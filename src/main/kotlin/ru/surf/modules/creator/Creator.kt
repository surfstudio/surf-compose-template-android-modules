package ru.surf.modules.creator

import org.apache.commons.io.FileUtils
import org.eclipse.jgit.api.Git
import org.slf4j.LoggerFactory
import java.io.File
import java.util.*

enum class CreatorModuleType {
    SIMPLE,
    MIDDLE,
    LARGER,
}

class Creator private constructor(
    private val path: String,
    private val name: String,
    private val repo: String,
    private val type: CreatorModuleType,
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    private val key = "_Module_name_"

    private val keyLowercase by lazy {
        key.lowercase()
    }

    private val applicationId: String by lazy {
        listOf(
            File("$path/app/build.gradle.kts"),
        ).firstOrNull {
            it.exists() && it.isFile
        }?.let {
            return@lazy loadApplicationId(it) ?: throw RuntimeException("applicationId null!")
        } ?: throw RuntimeException("Not found build.gradle.kts app!")
    }

    companion object {

        private const val REPO = "https://github.com/surfstudio/surf-compose-template-android-modules.git"

        fun run(type: CreatorModuleType, path: String, name: String) {
            // impl
            val creator = Creator(
                repo = REPO,
                path = path,
                name = name,
                type = type
            )

            // module dir
            val moduleDir = File("$path/modules/$name")

            // Clone module
            creator.cloneModule(moduleDir)
            // Move package
            creator.movePackage(moduleDir)
            // Connect to project
            creator.connectToProject()
        }
    }

    private fun loadApplicationId(gradleFile: File): String? {
        for (line in gradleFile.readLines()) {
            if (line.contains("applicationId")) {
                return line.substringAfter("\"").substringBefore("\"")
            }
        }
        return null
    }

    private fun cloneModule(moduleDir: File) {
        val temp = FileUtils.getTempDirectory().resolve(UUID.randomUUID().toString())

        val modulesDir = File("$path/modules")

        when {
            // create folder modules
            !modulesDir.exists() -> FileUtils.forceMkdir(modulesDir)
            // check if exist
            modulesDir.exists() && modulesDir.isFile -> throw RuntimeException("$modulesDir must to be a folder!")
            // check if exist module by name
            moduleDir.exists() -> throw RuntimeException("$moduleDir already exist!")
        }

        // clone repo
        Git.cloneRepository()
            .setURI(repo)
            .setDirectory(temp)
            .call()

        // move module
        FileUtils.moveDirectory(File("${temp.path}/modules/${type.name.lowercase()}"), moduleDir)

        // remove temp files
        FileUtils.deleteDirectory(temp)
    }

    private fun movePackage(moduleDir: File) {

        val segmentThird = File("${moduleDir.path}/src/main/kotlin/ru/surf/$keyLowercase")
        val segmentSecond = File("${moduleDir.path}/src/main/kotlin/ru/surf")
        val segmentOne = File("${moduleDir.path}/src/main/kotlin/ru")

        FileUtils.moveDirectory(segmentThird, File(segmentThird.path.replace(keyLowercase, name)))

        val segmentsApp = applicationId.split(".")

        if (segmentsApp.size < 3) {
            throw RuntimeException("Expected 3 segments per applicationId!")
        }

        try {
            FileUtils.moveDirectory(segmentSecond, File(segmentSecond.path.replace("surf", segmentsApp[1])))
        } catch (ex: Exception) {
            log.info(ex.message)
        }

        try {
            FileUtils.moveDirectory(segmentOne, File(segmentOne.path.replace("ru", segmentsApp[0])))
        } catch (ex: Exception) {
            log.info(ex.message)
        }
    }

    private fun connectToProject() {
        // add to settings.gradle.kts
        File(path).resolve("settings.gradle.kts").let { file ->
            var fileContent = ""
            file.forEachLine {
                if (it.contains("include(\":modules:core\")")) {
                    fileContent += "include(\":modules:$name\")\n"
                }
                fileContent += "$it\n"
            }
            file.printWriter().use { out ->
                out.println(fileContent)
            }
        }
        // add to build.gradle.kts
        File(path).resolve("app/build.gradle.kts").let { file ->
            var fileContent = ""
            file.forEachLine {
                if (it.contains("implementation(project(\":modules:core\"))")) {
                    fileContent += "    implementation(project(\":modules:$name\"))\n"
                }
                fileContent += "$it\n"
            }
            file.printWriter().use { out ->
                out.println(fileContent)
            }
        }
    }

    private fun changeForModuleName() {
        // AndroidManifest.xml
        // package ru.surf.users
        // UsersPreferences
        // provideUsersPreferences
        // UsersNavActions
        // UsersViewModel

    }
}