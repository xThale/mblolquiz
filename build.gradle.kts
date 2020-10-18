import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    application
}
group = "info.thale"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    implementation("org.seleniumhq.selenium:selenium-firefox-driver:3.14.0")
    implementation("org.seleniumhq.selenium:selenium-server:3.14.0")
    implementation("org.seleniumhq.selenium:selenium-java:3.14.0")
    testImplementation(kotlin("test-junit"))
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
application {
    mainClassName = "MainKt"
}