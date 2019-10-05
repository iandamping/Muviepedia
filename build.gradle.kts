// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath("com.google.gms:google-services:4.2.0")
        classpath(BuildPlugins.androidGradlePlugin)
        classpath(BuildPlugins.kotlinGradlePlugin)
        classpath(NavigationLibraies.safeArgsNavigationStdLib)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri(JitpackPlugins.jitpack) }
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}

