plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.mongodb.passkeeper.android"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val composeVersion = "1.1.1"

    implementation(project(":shared"))

    implementation("androidx.activity:activity-compose:1.4.0")

    implementation("androidx.compose.ui:ui:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    implementation ("androidx.compose.runtime:runtime-livedata:$composeVersion")

    implementation("androidx.compose.material:material:$composeVersion")
    implementation ("androidx.compose.material:material-icons-extended:$composeVersion")


    compileOnly("io.realm.kotlin:library-sync:0.11.0")
}