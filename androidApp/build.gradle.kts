plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.koin.compiler)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.android.junit5)
}

dependencies {
    implementation(projects.composeApp)
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.uiToolingPreview)
    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.koin.android)
    implementation(libs.koin.workmanager)
    implementation(project.dependencies.platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)

    androidTestImplementation(platform(libs.junit.bom))
    androidTestImplementation(libs.junit.jupiter.api)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.compose.uiTestAndroid)
    debugImplementation(libs.compose.uiTestManifest)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.params)
    testRuntimeOnly(libs.junit.jupiter.engine)
}

android {
    namespace = "pl.nepapp.rasoth"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "pl.nepapp.rasoth"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    signingConfigs {
        create("testSigning") {
            storeFile = rootProject.file("testSigning.jks")
            storePassword = "P@ssw0rd"
            keyAlias = "rasoth"
            keyPassword = "P@ssw0rd"
        }
    }
    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("testSigning")
        }
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}

junitPlatform {
    instrumentationTests.includeExtensions.set(true)
}
