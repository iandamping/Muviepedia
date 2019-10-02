import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.android.build.gradle.internal.dsl.BuildType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.1")
    defaultConfig {
        applicationId = "com.ian.app.muviepedia"
        minSdkVersion(19)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        initDebug(this@android)
        initRelease(this@android)
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_10
        targetCompatibility = JavaVersion.VERSION_1_10
    }
    dataBinding {
        isEnabled = true
    }

}
tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":navigation"))
    implementation(project(":movie"))
    implementation(project(":tvshow"))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(CoreLibraries.kotlinStdLib)
    implementation(CoreLibraries.appCompatStdLib)
    implementation(CoreLibraries.androidXCoreStdLib)
    implementation(CoreLibraries.constraintLayoutStdLib)
    implementation(CoreLibraries.materialDesignStdLib)
    implementation(CoreLibraries.multidexStdLib)
    testImplementation(TestLibraries.jUnit4StdLib)
    testImplementation(TestLibraries.mockitoCoreStdLib)
    androidTestImplementation(TestLibraries.testRunnerStdLib)
    androidTestImplementation(TestLibraries.espressoCoreStdLib)
    implementation(CustomLibraries.commonHelperStdLib)
    implementation(NavigationLibraies.navigationUiStdLib)
    implementation(WorkerLibrary.workManagerStdLib)
    implementation(KoinLibraries.koinStdLib)

}

fun NamedDomainObjectContainer<BuildType>.initDebug(proguard: BaseAppModuleExtension) {
    this.getByName("debug") {
        isMinifyEnabled = false
        isShrinkResources = false
        proguardFiles(proguard.getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
}

fun NamedDomainObjectContainer<BuildType>.initRelease(proguard: BaseAppModuleExtension) {
    this.getByName("release") {
        isMinifyEnabled = false
        isShrinkResources = false
        proguardFiles(proguard.getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
}
