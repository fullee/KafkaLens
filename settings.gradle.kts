pluginManagement {
    repositories {
        maven("https://maven.aliyun.com/repository/public")
        maven("https://mirrors.tuna.tsinghua.edu.cn/nexus/content/groups/public/")
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
    }

    plugins {
        kotlin("jvm").version(extra["kotlin.version"] as String)
        id("org.jetbrains.compose").version(extra["compose.version"] as String)
        id("org.jetbrains.kotlin.plugin.compose").version(extra["kotlin.version"] as String)
    }
}

rootProject.name = "demo"
