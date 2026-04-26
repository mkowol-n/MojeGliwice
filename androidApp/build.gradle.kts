plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.koin.compiler)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
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
}
