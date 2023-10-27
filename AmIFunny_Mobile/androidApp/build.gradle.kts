plugins {
    kotlin("kapt")
    id("com.android.application")
    kotlin("android")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.teegarcs.amifunny.android"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "com.teegarcs.amifunny.android"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//            excludes += "/META-INF/gradle/incremental.annotation.processors/**"
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
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.compose.ui)
    implementation(libs.compose.tooling)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.activity)
    implementation(libs.activityx)
    implementation(libs.fragmentx)
    implementation(libs.androidx.core)
    implementation(libs.compatx)
    implementation(libs.constraint.classic)
    implementation(libs.constraint.compose)
    implementation(libs.nav.fragment)
    implementation(libs.nav.ui)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
//    implementation(libs.hilt.compiler)
//    implementation(libs.hilt.compose)
//    implementation(libs.hilt.compose.compiler)
}