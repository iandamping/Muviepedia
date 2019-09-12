import com.android.build.gradle.internal.dsl.BuildType

plugins {
    id("com.android.application")
    id("androidx.navigation.safeargs")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(28)
    buildToolsVersion("29.0.1")
    defaultConfig {
        applicationId = "com.ian.junemon.spe_learning_mvvm"
        minSdkVersion(16)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        initBuildCondigField(this)
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dataBinding {
        isEnabled = true
    }
    /*androidExtensions {
        isExperimental = true
    }*/
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
    implementation(NetworkingLibraries.retrofitStdLib)
    implementation(NetworkingLibraries.retrofitGsonConverterStdLib)
    implementation(NetworkingLibraries.okhttp3LoggingInterceptorStdLib)
    implementation(NetworkingLibraries.okhttp3StdLib)
    implementation(NetworkingLibraries.gsonStdLib)
    implementation(LottieLibrary.lottieStdLib)
    implementation(ShimmerLibrary.shimmerStdLib)
    implementation(NavigationLibraies.navigationStdLib)
    implementation(NavigationLibraies.navigationUiStdLib)
    implementation(NavigationLibraies.legacySupportStdLib)
}

fun initBuildCondigField(data: NamedDomainObjectContainer<BuildType>) {
    data.forEach {
        it.buildConfigField("String", "movieApiKey", ConfigKey.movie_api_key)
        it.buildConfigField("String", "baseUrl", ConfigKey.base_url)
        it.buildConfigField("String", "imageFormatter", ConfigKey.imageFormatter)
        it.buildConfigField("String", "popularMovie", ConfigKey.popularMovie)
        it.buildConfigField("String", "searchMovie", ConfigKey.searchMovie)
        it.buildConfigField("String", "detailMovie", ConfigKey.detailMovie)
        it.buildConfigField("String", "popularTv", ConfigKey.popularTv)
        it.buildConfigField("String", "searchTvShows", ConfigKey.searchTvShows)
        it.buildConfigField("String", "detailTv", ConfigKey.detailTv)
        it.buildConfigField("String", "similarTv", ConfigKey.similarTv)
        it.buildConfigField("String", "topRatedTv", ConfigKey.topRatedTv)
        it.buildConfigField("String", "airingTodayTv", ConfigKey.airingTodayTv)
        it.buildConfigField("String", "onAirTv", ConfigKey.onAirTv)
        it.buildConfigField("String", "nowPlayingMovie", ConfigKey.nowPlayingMovie)
        it.buildConfigField("String", "topRatedMovie", ConfigKey.topRatedMovie)
        it.buildConfigField("String", "upComingMovie", ConfigKey.upComingMovie)
        it.buildConfigField("String", "similarMovie", ConfigKey.similarMovie)
    }
}
