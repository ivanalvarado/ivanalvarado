plugins {
    kotlin("jvm") version libs.versions.kotlin.get()
    application
}

group = "com.ivanalvarado"
version = libs.versions.app.version.get()

repositories {
    mavenCentral()
}

application {
    mainClass.set("com.ivanalvarado.readme.ReadMeUpdaterCommandKt")
}

dependencies {
    implementation(libs.clikt)
    testImplementation(libs.kotlin.test)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(libs.versions.jdk.get().toInt())
}
