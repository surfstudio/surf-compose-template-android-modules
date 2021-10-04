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

    private val applicationId: String by lazy {
        listOf(
            File("$path/app/build.gradle.kts"),
            File("$path/app/build.gradle"),
        ).firstOrNull {
            it.exists() && it.isFile
        }?.let {
            return@lazy loadApplicationId(it) ?: throw RuntimeException("applicationId null!")
        } ?: throw RuntimeException("Not found build gradle app!")
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

        val segmentThird = File("${moduleDir.path}/src/main/kotlin/ru/surf/module_name")
        val segmentSecond = File("${moduleDir.path}/src/main/kotlin/ru/surf")
        val segmentOne = File("${moduleDir.path}/src/main/kotlin/ru")

        FileUtils.moveDirectory(segmentThird, File(segmentThird.path.replace("module_name", name)))

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
}