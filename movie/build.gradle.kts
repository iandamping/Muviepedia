plugins {
    id("com.android.library")
    id("androidx.navigation.safeargs")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}
android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.1")
    defaultConfig {
        minSdkVersion(19)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFile("consumer-rules.pro")
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

        }
        getByName("release") {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

        }
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_10
        targetCompatibility = JavaVersion.VERSION_1_10
    }
    dataBinding {
        isEnabled = true
    }

}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(CoreLibraries.kotlinStdLib)
    implementation(CoreLibraries.appCompatStdLib)
    implementation(CoreLibraries.androidXCoreStdLib)
    implementation(CoreLibraries.constraintLayoutStdLib)
    implementation(CoreLibraries.materialDesignStdLib)
    testImplementation(TestLibraries.jUnit4StdLib)
    testImplementation(TestLibraries.mockitoCoreStdLib)
    androidTestImplementation(TestLibraries.testRunnerStdLib)
    androidTestImplementation(TestLibraries.espressoCoreStdLib)

    implementation(CustomLibraries.commonHelperStdLib)
    implementation(CustomLibraries.recyclerHelperStdLib)
    implementation(CustomLibraries.cirlceIndicatorStdLib)
    implementation(KoinLibraries.koinStdLib)
    implementation(LottieLibrary.lottieStdLib)
    implementation(ShimmerLibrary.shimmerStdLib)
    implementation(PagingLibrary.pagingStdLib)
    implementation(NavigationLibraies.navigationStdLib)
    implementation(NavigationLibraies.legacySupportStdLib)
    implementation(project(":domain"))
    implementation(project(":presentation"))

    /*implementation(LifecycleLibraries.lifecycleRuntime)
    implementation(LifecycleLibraries.viewmodelKtxStdLib)
    implementation(LifecycleLibraries.liveDataKtxStdLib)*/
}



