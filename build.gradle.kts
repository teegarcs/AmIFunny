import org.springframework.boot.gradle.tasks.bundling.BootWar

plugins {
	war
	id("org.springframework.boot") version "3.1.3"
	id("io.spring.dependency-management") version "1.1.3"
	id ("org.jmailen.kotlinter") version "3.4.5"
	kotlin("jvm") version "1.9.0"
	kotlin("plugin.serialization") version "1.9.0"
	kotlin("plugin.spring") version "1.9.10"
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

val kotlinSerializationVersion: String by project

allprojects {
	repositories {
		mavenCentral()
	}
}

subprojects {

	apply {
		plugin("org.jetbrains.kotlin.jvm")
		plugin ("org.jmailen.kotlinter")
		plugin ("org.jetbrains.kotlin.plugin.serialization")
	}

	java {
		sourceCompatibility = JavaVersion.VERSION_17
	}

	dependencies {
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")
	}
}

tasks.withType<BootWar> {
	mainClass.set("com.aifunny.AIFunnyAppKt")
}

dependencies {
	implementation(project(":aifunny-app"))
	implementation(project(":aifunny-model"))
}