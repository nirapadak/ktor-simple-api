val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val kmongo_version = "4.8.0"


plugins {
    kotlin("jvm") version "1.9.22"
    id("io.ktor.plugin") version "2.2.3"
}

group = "org.example"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("org.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-gson-jvm:$ktor_version")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")


    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")

    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // KMongo ------------
    implementation("io.ktor:ktor-server-netty:$ktor_version") // latest: 2.2.3
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("org.litote.kmongo:kmongo-coroutine:$kmongo_version") // latest: 4.8.0
}

tasks.test {
    useJUnitPlatform()
}