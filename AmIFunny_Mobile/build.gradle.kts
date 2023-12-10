
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("android") version "1.9.21" apply false
    kotlin("multiplatform") version libs.versions.kotlin.get() apply false
    kotlin("plugin.serialization") version libs.versions.kotlin.get() apply false
    id("com.android.application") version libs.versions.android.gradle.plugin.get() apply false
    id("com.android.library") version libs.versions.android.gradle.plugin.get() apply false
    id("com.google.devtools.ksp") version libs.versions.ksp.get() apply false
    id("com.squareup.sqldelight") version libs.versions.sqlDelight.get() apply false
    id("com.rickclephas.kmp.nativecoroutines") version libs.versions.kmpNativecoroutines.get() apply false
    id("com.google.dagger.hilt.android") version libs.versions.hilt.get() apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://androidx.dev/storage/compose-compiler/repository/")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev/")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
