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
    mainClass.set("com.ivanalvarado.readme.UpdateReadMeCommandKt")
}

dependencies {
    implementation(libs.clikt)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.moshi)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.okio)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    testImplementation(libs.junit)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlinx.coroutines.test)
}

tasks.test {
    useJUnit()
}

kotlin {
    jvmToolchain(libs.versions.jdk.get().toInt())
}
