plugins {

    application
    kotlin("jvm")

    // https://github.com/diffplug/spotless
    id("com.diffplug.spotless")
    // https://github.com/johnrengelman/shadow
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

spotless {
    kotlin {
        target("**/*.kt")
        licenseHeaderFile("${project.projectDir}/copyright.txt")
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint()
    }
}

group = "ru.surf.modules"
version = "0.0.1"

application {
    mainClass.set("ru.surf.modules.ApplicationKt")
}

repositories {
    mavenCentral()
}

tasks {
    shadowJar {

        archiveBaseName.set("modules")

        manifest {
            attributes(Pair("Main-Class", "ru.surf.modules.ApplicationKt"))
        }
    }
}

dependencies {
    // di
    implementation("io.insert-koin:koin-ktor:${libs.versions.koinVersion.get()}")
    // other
    implementation("ch.qos.logback:logback-classic:${libs.versions.logbackVersion.get()}")
    // CLI
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.3")
    implementation("org.eclipse.jgit:org.eclipse.jgit:3.5.0.201409260305-r")
    implementation("commons-io:commons-io:2.6")
}
