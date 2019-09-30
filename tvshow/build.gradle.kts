
plugins {
    id("com.android.library")
    id("androidx.navigation.safeargs")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}
android {
    compileSdkVersion(28)
    buildToolsVersion("29.0.1")
    defaultConfig {
        minSdkVersion(16)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFile("consumer-rules.pro")
    }

    buildTypes {
        getByName("debug"){
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

        }
        getByName("release"){
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
    implementation(CoreLibraries.multidexStdLib)
    testImplementation(TestLibraries.jUnit4StdLib)
    testImplementation(TestLibraries.mockitoCoreStdLib)
    androidTestImplementation(TestLibraries.testRunnerStdLib)
    androidTestImplementation(TestLibraries.espressoCoreStdLib)
    implementation(CoroutineLibraries.coroutineCoreStdLib)
    implementation(CoroutineLibraries.coroutineStdLib)
    implementation(CustomLibraries.commonHelperStdLib)
    implementation(CustomLibraries.recyclerHelperStdLib)
    implementation(CustomLibraries.cirlceIndicatorStdLib)
    implementation(KoinLibraries.koinStdLib)
    implementation(LifecycleLibraries.lifecycleLib)
    kapt(LifecycleLibraries.lifecycleCompilerStdLib)
    implementation(LifecycleLibraries.viewmodelKtxStdLib)
    implementation(LifecycleLibraries.liveDataKtxStdLib)
    implementation(LottieLibrary.lottieStdLib)
    implementation(ShimmerLibrary.shimmerStdLib)
    implementation(PagingLibrary.pagingStdLib)
    implementation(NavigationLibraies.navigationStdLib)
    implementation(NavigationLibraies.navigationUiStdLib)
    implementation(NavigationLibraies.legacySupportStdLib)
    implementation(project(":data"))
}

