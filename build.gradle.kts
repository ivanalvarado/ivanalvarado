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
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.moshi)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    testImplementation(libs.kotlin.test)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(libs.versions.jdk.get().toInt())
}
