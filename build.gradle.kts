plugins {
    val kotlinVersion = "1.9.22"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "ru.golovanov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.kafka:spring-kafka")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}