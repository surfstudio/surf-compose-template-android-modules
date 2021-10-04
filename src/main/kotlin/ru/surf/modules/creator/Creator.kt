package ru.surf.modules.creator

import org.apache.commons.io.FileUtils
import org.eclipse.jgit.api.Git
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

    private lateinit var applicationId: String

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
            // Clone module
            creator.cloneModule()
            // Move package
            creator.movePackage()
        }
    }

    init {
        // load info about app
        listOf(
            File("$path/app/build.gradle.kts"),
            File("$path/app/build.gradle"),
        ).firstOrNull {
            it.exists() && it.isFile
        }?.let {
            loadApplicationId(it)?.let { id -> applicationId = id }
        } ?: throw RuntimeException("Not found build gradle app!")

        // is not found applicationId
        if (!::applicationId.isInitialized) {
            throw RuntimeException("applicationId null!")
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

    private fun cloneModule() {
        val temp = FileUtils.getTempDirectory().resolve(UUID.randomUUID().toString())

        val modulesDir = File("$path/modules")
        val newModuleDir = File("$path/modules/$name")

        when {
            // create folder modules
            !modulesDir.exists() -> FileUtils.forceMkdir(modulesDir)
            // check if exist
            modulesDir.exists() && modulesDir.isFile -> throw RuntimeException("$modulesDir must to be a folder!")
            // check if exist module by name
            newModuleDir.exists() -> throw RuntimeException("$newModuleDir already exist!")
        }

        // clone repo
        Git.cloneRepository()
            .setURI(repo)
            .setDirectory(temp)
            .call()

        // move module
        FileUtils.moveDirectory(File("${temp.path}/modules/${type.name.lowercase()}"), newModuleDir)

        // remove temp files
        FileUtils.deleteDirectory(temp)
    }

    private fun movePackage() {
        val newModuleDir = File("$path/modules/$name")

        val segmentThird = File("${newModuleDir.path}/src/main/kotlin/ru/surf/module_name")
        val segmentSecond = File("${newModuleDir.path}/src/main/kotlin/ru/surf")
        val segmentOne = File("${newModuleDir.path}/src/main/kotlin/ru")

        FileUtils.moveDirectory(segmentThird, File(segmentThird.path.replace("module_name", name)))

        val segmentsApp = applicationId.split(".")

        if (segmentsApp.size < 3) {
            throw RuntimeException("Expected 3 segments per applicationId!")
        }

        FileUtils.moveDirectory(segmentSecond, File(segmentSecond.path.replace("surf", segmentsApp[1])))
        FileUtils.moveDirectory(segmentOne, File(segmentOne.path.replace("ru", segmentsApp[0])))
    }
}