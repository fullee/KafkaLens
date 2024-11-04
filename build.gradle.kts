import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm").version("2.0.20")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.devtools.ksp").version("2.0.20-1.0.24")
    id("app.cash.sqldelight").version("2.0.2")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.aliyun.com/repository/public")
    maven("https://mirrors.tuna.tsinghua.edu.cn/nexus/content/groups/public/")
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-debug:1.8.1")
    implementation("org.apache.kafka:kafka-clients:3.8.0")
    implementation("org.apache.kafka:kafka-streams:3.8.0")
    implementation("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")
    implementation("io.insert-koin:koin-core:3.5.6")
    implementation("io.insert-koin:koin-compose:1.1.5")
    api("io.insert-koin:koin-annotations:1.3.1")
    ksp("io.insert-koin:koin-ksp-compiler:1.3.1")
    implementation("app.cash.sqldelight:sqlite-driver:2.0.2")
    implementation("app.cash.sqldelight:coroutines-extensions:2.0.2")
    implementation("com.formdev:flatlaf:3.5.2")
    implementation("com.formdev:flatlaf-intellij-themes:3.5.2")
}

compose.desktop {
    application {
        mainClass = "org.oskwg.kafkalens.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "demo1"
            packageVersion = "1.0.0"
            modules("java.management", "java.security.jgss")
        }

        buildTypes {
            release {
                proguard {
                    configurationFiles.from("proguard-rules.pro")
                }
            }
        }
    }
}
ksp {
    arg("KOIN_CONFIG_CHECK","true")
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.example")
        }
    }
}
//sourceSets.main {
//    java.srcDirs("build/generated/ksp/main/kotlin")
//}