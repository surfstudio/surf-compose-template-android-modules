val configurationSource: Any.() -> Unit by project.extra
val dependenciesInternal: Any.() -> Unit by project.extra

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {

    compileSdk = findProperty("compileSdk").toString().toInt()

    defaultConfig {

        minSdk = findProperty("minSdk").toString().toInt()
        targetSdk = findProperty("targetSdk").toString().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }

    buildFeatures {
        compose = true
    }

    // common configuration source
    this.sourceSets.configurationSource()

    android.sourceSets {
        sourceSets {

            println(this.names)

            getByName("test").java.srcDir("$projectDir/modules/core/src")
        }
    }

    // disable waring OptIn
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
}

// libraries
dependencies {

    // Common module for all modules
    implementation(project(":modules:core"))

    // Connecting internal libraries
    dependenciesInternal()

    implementation(libs.bundles.other)
    implementation(libs.bundles.lottie)
    implementation(libs.bundles.firebase)
    implementation(libs.bundles.accompanist)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.hilt)

    kapt(libs.bundles.hiltKapt)
}