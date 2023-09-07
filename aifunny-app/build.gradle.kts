import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("io.spring.dependency-management")
    id("org.springframework.boot")
    kotlin("plugin.spring")
}

val kotlinSerializationVersion: String by project

tasks.jar {
    enabled = true
}

tasks.withType<BootJar> {
    archiveClassifier.set("application")
}

tasks.withType<Exec> {
    environment["PALM_API_KEY"] = "SOMETHING FAKE"
}
dependencies {
    implementation(project(":aifunny-model"))
   	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")

    //gen AI
    implementation("com.google.cloud:gapic-google-cloud-ai-generativelanguage-v1beta2-java:0.0.0-SNAPSHOT")
    implementation("io.grpc:grpc-okhttp:1.53.0")
}