/**
 * Copyright 2021 Surf
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.surf.modules.creator

import org.apache.commons.io.FileUtils
import org.eclipse.jgit.api.Git
import org.slf4j.LoggerFactory
import ru.surf.modules.common.base.toBlue
import ru.surf.modules.common.base.toGreen
import ru.surf.modules.common.extensions.getPackageFromAndroidManifest
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

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

    private val nameLowercase by lazy {
        name.replaceFirstChar { it.lowercase() }
    }

    private val namePackage by lazy {
        name.map { it.toString() }.reduce { acc, c -> acc + if (c.first().isUpperCase()) "_$c" else c }.lowercase()
    }

    private val packetCore: List<String> by lazy {
        File(path).resolve("modules/core/src/main/AndroidManifest.xml").getPackageFromAndroidManifest()
    }

    private val packet: List<String> by lazy {
        File(path).resolve("$appName/src/main/AndroidManifest.xml").getPackageFromAndroidManifest()
    }

    private val appModules: String by lazy {
        val module = appName.replace("app", "modules")
        if (File("${path}/${module}").exists()) {
            module
        } else {
            "modules"
        }
    }

    private val appName: String by lazy {
        val process = ProcessBuilder("\\s".toRegex().split("./gradlew projects"))
            .directory(File(path))
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()
            .also { it.waitFor(10, TimeUnit.SECONDS) }

        if (process.exitValue() != 0) {
            throw Exception(process.errorStream.bufferedReader().readText())
        }
        process.inputStream.bufferedReader().readText().split("\n")
            .first { it.contains("+--- Project") && !it.contains("modules") }
            .substringAfter(":").substringBefore("'")
    }

    companion object {

        private const val REPO = "https://github.com/surfstudio/surf-compose-template-android-modules.git"

        fun run(type: CreatorModuleType, path: String, name: String) {
            // impl
            val creator = Creator(
                repo = REPO,
                path = path,
                name = name.replaceFirstChar { it.titlecase(Locale.getDefault()) },
                type = type
            )

            // module dir
            val moduleDir = File("$path/${creator.appModules}/${creator.nameLowercase}")

            println(""" ${"???".toBlue()} Start clone module""")
            creator.cloneModule(moduleDir)

            println(""" ${"???".toBlue()} Start move package""")
            creator.movePackage(moduleDir)

            println(""" ${"???".toBlue()} Connect to project""")
            creator.connectToProject()

            println(""" ${"???".toBlue()} Connect to app""")
            creator.addActionsToApp()

            println(""" ${"???".toBlue()} Find and change name file to module name""")
            creator.renameFilesAndDirectory(moduleDir)

            println(""" ${"???".toBlue()} Find and change text in files to module name""")
            creator.changeNamesClassesAndFunctions(moduleDir)

            println()
            println(""" ${"Done !".toGreen()}""")
        }
    }

    private fun cloneModule(moduleDir: File) {

        val temp = FileUtils.getTempDirectory().resolve(UUID.randomUUID().toString())

        val modulesDir = File("$path/$appModules")

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

        FileUtils.moveDirectory(segmentThird, File(segmentThird.path.replace(keyLowercase, namePackage)))

        try {
            FileUtils.moveDirectory(segmentSecond, File(segmentSecond.path.replace("surf", packet[1])))
        } catch (ex: Exception) {
            log.info(ex.message)
        }

        try {
            FileUtils.moveDirectory(segmentOne, File(segmentOne.path.replace("ru", packet[0])))
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
                    fileContent += "include(\":$appModules:$nameLowercase\")\n"
                }
                fileContent += "$it\n"
            }
            file.printWriter().use { out ->
                out.println(fileContent.trim())
            }
        }
        // add to build.gradle.kts
        File(path).resolve("$appName/build.gradle.kts").let { file ->
            if (file.exists()) {
                var fileContent = ""
                file.forEachLine {
                    if (it.contains("implementation(project(\":modules:core\"))")) {
                        fileContent += "    implementation(project(\":$appModules:$nameLowercase\"))\n"
                    }
                    fileContent += "$it\n"
                }
                file.printWriter().use { out ->
                    out.println(fileContent)
                }
            }
        }
    }

    private fun addActionsToApp() {
        File(path).resolve("$appName/src/main/kotlin/${packet.joinToString("/")}/navigation/NavActions.kt").let { file ->
            if (file.exists()) {
                var fileContent = ""
                file.forEachLine {
                    fileContent +=
                        if (it.contains("import androidx.navigation.NavHostController")) {
                            "import androidx.navigation.NavHostController\n" +
                                    "import ${
                                        packet.take(2).joinToString(".")
                                    }.${namePackage}.navigation.actions.${name}NavActions\n"
                        } else if (it.contains(") : ")) {
                            val clazz = it.substringAfter(":").trim().replace(",", "")
                            ") : ${name}NavActions,\n    $clazz,\n"
                        } else {
                            "$it\n"
                        }
                }
                file.printWriter().use { out ->
                    out.println(fileContent)
                }
            }
        }
    }

    private fun renameFilesAndDirectory(moduleDir: File) {
        moduleDir.walkTopDown().forEach {
            if (it.isFile) {
                if (it.name.contains(key)) {
                    FileUtils.moveFile(it, File(it.path.replace(key, name)))
                } else if (it.name.contains(keyLowercase)) {
                    FileUtils.moveFile(it, File(it.path.replace(keyLowercase, nameLowercase)))
                }
            } else if (it.isDirectory) {
                if (it.name.contains(key)) {
                    FileUtils.moveDirectory(it, File(it.path.replace(key, name)))
                } else if (it.name.contains(keyLowercase)) {
                    FileUtils.moveDirectory(it, File(it.path.replace(keyLowercase, nameLowercase)))
                }
            }
        }
    }

    private fun changeNamesClassesAndFunctions(moduleDir: File) {
        moduleDir.walkTopDown().forEach { file ->
            if (file.isFile) {
                var isFind = false
                var fileContent = ""
                file.forEachLine { line ->
                    var lineRes = line
                    if (lineRes.contains(key)) {
                        isFind = true
                        lineRes = lineRes.replace(key, name)
                    }
                    if (lineRes.contains(keyLowercase)) {
                        isFind = true
                        lineRes = lineRes.replace(keyLowercase, nameLowercase)
                    }
                    // module package
                    if (lineRes.contains("ru.surf.$nameLowercase")) {
                        isFind = true
                        lineRes = lineRes.replace(
                            "ru.surf.$nameLowercase",
                            "${packet.take(2).joinToString(".")}.$namePackage"
                        )
                    }
                    // core package
                    if (lineRes.contains("ru.surf.core")) {
                        isFind = true
                        lineRes = lineRes.replace("ru.surf.core", packetCore.joinToString("."))
                    }
                    fileContent += "$lineRes\n"
                }
                if (isFind) {
                    file.printWriter().use { out ->
                        out.println(fileContent)
                    }
                }
            }
        }
    }
}