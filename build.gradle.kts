import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.devtools.ksp").version("2.0.20-1.0.24")
//    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://maven.aliyun.com/repository/public")
    maven("https://mirrors.tuna.tsinghua.edu.cn/nexus/content/groups/public/")
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation("org.apache.kafka:kafka-clients:3.8.0")
    implementation("org.apache.kafka:kafka-streams:3.8.0")
    implementation("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")
    implementation("io.insert-koin:koin-core:3.5.6")
    implementation("io.insert-koin:koin-annotations:1.3.1")
    implementation("io.insert-koin:koin-ksp-compiler:1.3.1")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "demo1"
            packageVersion = "1.0.0"
        }
    }
}

sourceSets.main {
    java.srcDirs("build/generated/ksp/main/kotlin")
}