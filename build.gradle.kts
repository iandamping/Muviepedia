// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
        maven { url = uri(FabricioPlugins.fabric) }
    }
    dependencies {
        classpath("com.google.gms:google-services:4.2.0")
        classpath(BuildPlugins.androidGradlePlugin)
        classpath(BuildPlugins.kotlinGradlePlugin)
        classpath(BuildPlugins.fabricPlugin)
        classpath(NavigationLibraies.safeArgsNavigationStdLib)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
plugins{
    id("org.jlleitschuh.gradle.ktlint") version ("7.1.0")
}
allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri(JitpackPlugins.jitpack) }
    }
    apply { plugin( "org.jlleitschuh.gradle.ktlint") }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}

