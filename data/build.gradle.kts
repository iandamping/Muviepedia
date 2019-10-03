import com.android.build.gradle.internal.dsl.BuildType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
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
        multiDexEnabled = true
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
        initBuildCondigField(this)
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_10
        targetCompatibility = JavaVersion.VERSION_1_10
    }
}
tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
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
        it.buildConfigField("String", "workerName", ConfigKey.workerName)
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
    implementation(PagingLibrary.pagingStdLib)
    implementation(RoomLibraies.roomStdLib)
    implementation(RoomLibraies.roomKtxStdLib)
    implementation(WorkerLibrary.workManagerStdLib)
    implementation(GlidePlugins.glidePlugin)
    kapt(GlidePlugins.glideCompllierPlugin)
    kapt(RoomLibraies.roomCompilerStdLib)
}
