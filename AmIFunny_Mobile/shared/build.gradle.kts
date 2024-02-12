plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("com.squareup.sqldelight")
    id("com.google.devtools.ksp")
    id("co.touchlab.skie") version "0.5.6"
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        all {
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }

        val commonMain by getting {
            dependencies {
                implementation(libs.sqlDelight.coroutinesExt)

                implementation(libs.kotlinx.datetime)
                implementation(libs.coroutines.core)

                implementation(libs.kermit)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.client.contentNegotiation)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.sqlDelight.android)
                implementation(libs.ktor.client.android)
                implementation(libs.androidx.lifecycle.viewmodel)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.sqlDelight.native)
                implementation(libs.ktor.client.ios)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.teegarcs.amifunny"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
}
dependencies {
    implementation("androidx.core:core-ktx:+")
    implementation("androidx.core:core-ktx:+")
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
}
