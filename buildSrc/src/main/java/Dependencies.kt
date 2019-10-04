import AndroidXVersions.appCompatVer
import AndroidXVersions.buildToolsVersion
import AndroidXVersions.cirlceIndicatorRelexVer
import AndroidXVersions.commonHelperVer
import AndroidXVersions.constraintLayoutVer
import AndroidXVersions.coreVer
import AndroidXVersions.coroutineVer
import AndroidXVersions.espressoCoreVer
import AndroidXVersions.facebookSdkVer
import AndroidXVersions.firebaseAuthUiVer
import AndroidXVersions.firebaseAuthVer
import AndroidXVersions.firebaseCoreVer
import AndroidXVersions.firebaseDatabaseVer
import AndroidXVersions.firebaseStorageVer
import AndroidXVersions.glideVer
import AndroidXVersions.gmsServiceVer
import AndroidXVersions.gsonVer
import AndroidXVersions.jUnitVer
import AndroidXVersions.koinVer
import AndroidXVersions.kotlinVer
import AndroidXVersions.legacySupport
import AndroidXVersions.lifecycleVer
import AndroidXVersions.lottiVer
import AndroidXVersions.materialVer
import AndroidXVersions.mockitoCoreVer
import AndroidXVersions.multidexVer
import AndroidXVersions.navigationVer
import AndroidXVersions.okHttp3LogginInterceptorVer
import AndroidXVersions.okHttp3Ver
import AndroidXVersions.pagingRuntimeVer
import AndroidXVersions.picassoVersion
import AndroidXVersions.recyckerHelperVer
import AndroidXVersions.retrofitVer
import AndroidXVersions.roomVer
import AndroidXVersions.rxjava2Ver
import AndroidXVersions.shimmerVer
import AndroidXVersions.testRunnerVer
import AndroidXVersions.workerVersion

/**
 *
Created by Ian Damping on 05/09/2019.
Github = https://github.com/iandamping
 */

object AndroidXVersions {
    const val buildToolsVersion = "3.4.2"
    const val kotlinVer = "1.3.50"
    const val pagingRuntimeVer = "1.0.1"
    const val coroutineVer = "1.3.1"
    const val commonHelperVer = "0.0.15-alpha02"
    const val recyckerHelperVer = "0.0.5-alpha04c"
    const val firebaseCoreVer = "17.1.0"
    const val firebaseDatabaseVer = "19.0.0"
    const val firebaseAuthVer = "19.0.0"
    const val firebaseStorageVer = "19.0.0"
    const val firebaseAuthUiVer = "4.1.0"
    const val facebookSdkVer = "4.39.0"
    const val multidexVer = "1.0.3"
    const val koinVer = "2.0.1"
    const val lifecycleVer = "2.2.0-alpha03"
    const val mockitoCoreVer = "2.5.3"
    const val jUnitVer = "4.12"
    const val testRunnerVer = "1.3.0-alpha02"
    const val espressoCoreVer = "3.3.0-alpha02"
    const val appCompatVer = "1.1.0-rc01"
    const val coreVer = "1.2.0-alpha03"
    const val constraintLayoutVer = "2.0.0-beta2"
    const val materialVer = "material:1.0.0"
    const val retrofitVer = "2.6.1"
    const val okHttp3Ver = "3.12.0"
    const val okHttp3LogginInterceptorVer = "3.11.0"
    const val rxjava2Ver = "2.1.0"
    const val gsonVer = "2.8.5"
    const val glideVer = "4.9.0"
    const val gmsServiceVer = "4.3.1"
    const val roomVer = "2.2.0-beta01"
    const val lottiVer = "3.0.7"
    const val navigationVer = "2.2.0-alpha03"
    const val shimmerVer = "0.5.0"
    const val cirlceIndicatorRelexVer = "1.2.2@aar"
    const val legacySupport = "1.0.0"
    const val workerVersion = "2.2.0"
    const val picassoVersion = "2.5.2"
}

object JitpackPlugins {
    const val jitpack = "https://jitpack.io"
}

object BuildPlugins {
    const val androidGradlePlugin = "com.android.tools.build:gradle:$buildToolsVersion"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVer"
}

object RoomLibraies{
    const val roomStdLib = "androidx.room:room-runtime:$roomVer"
    const val roomCompilerStdLib = "androidx.room:room-compiler:$roomVer"
    const val roomKtxStdLib = "androidx.room:room-ktx:$roomVer"
}

object NavigationLibraies{
    const val navigationStdLib = "androidx.navigation:navigation-fragment-ktx:$navigationVer"
    const val navigationUiStdLib = "androidx.navigation:navigation-ui-ktx:$navigationVer"
    const val legacySupportStdLib = "androidx.legacy:legacy-support-v4:$legacySupport"
    const val safeArgsNavigationStdLib = "androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVer"
}

object LottieLibrary{
    const val lottieStdLib =  "com.airbnb.android:lottie:$lottiVer"
}


object GlidePlugins {
    const val glidePlugin = "com.github.bumptech.glide:glide:$glideVer"
    const val glideCompllierPlugin = "com.github.bumptech.glide:compiler:$glideVer"
}

object PicassoPlugins{
    const val picassoPlugin = "com.squareup.picasso:picasso:$picassoVersion"
}

object CoreLibraries {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVer"
    const val appCompatStdLib = "androidx.appcompat:appcompat:$appCompatVer"
    const val androidXCoreStdLib = "androidx.core:core-ktx:$coreVer"
    const val constraintLayoutStdLib = "androidx.constraintlayout:constraintlayout:$constraintLayoutVer"
    const val materialDesignStdLib = "com.google.android.material:$materialVer"
    const val multidexStdLib = "com.android.support:multidex:$multidexVer"
}

object NetworkingLibraries {
    const val retrofitStdLib = "com.squareup.retrofit2:retrofit:$retrofitVer"
    const val retrofitGsonConverterStdLib = "com.squareup.retrofit2:converter-gson:$retrofitVer"
    const val okhttp3LoggingInterceptorStdLib = "com.squareup.okhttp3:logging-interceptor:$okHttp3LogginInterceptorVer"
    const val okhttp3StdLib = "com.squareup.okhttp3:okhttp:$okHttp3Ver"
    const val gsonStdLib = "com.google.code.gson:gson:$gsonVer"
}

object RxJava2Library {
    const val rxjava2StdLib = "io.reactivex.rxjava2:rxandroid:$rxjava2Ver"
}

object KoinLibraries {
    const val koinStdLib = "org.koin:koin-android-viewmodel:$koinVer"
}

object LifecycleLibraries {
    const val lifecycleLib = "androidx.lifecycle:lifecycle-extensions:$lifecycleVer"
    const val lifecycleCompilerStdLib = "androidx.lifecycle:lifecycle-compiler:$lifecycleVer"
    const val viewmodelKtxStdLib = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVer"
    const val liveDataKtxStdLib = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVer"
}

object CoroutineLibraries {
    const val coroutineCoreStdLib = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVer"
    const val coroutineStdLib = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVer"
}

object TestLibraries {
    const val jUnit4StdLib = "junit:junit:$jUnitVer"
    const val mockitoCoreStdLib = "org.mockito:mockito-core:$mockitoCoreVer"
    const val testRunnerStdLib = "androidx.test:runner:$testRunnerVer"
    const val espressoCoreStdLib = "androidx.test.espresso:espresso-core:$espressoCoreVer"
}

object CustomLibraries {
    const val recyclerHelperStdLib = "com.github.iandamping:RecyclerViewHelper:$recyckerHelperVer"
    const val commonHelperStdLib = "com.github.iandamping:CommonHelper:$commonHelperVer"
    const val cirlceIndicatorStdLib = "me.relex:circleindicator:$cirlceIndicatorRelexVer"
}

object ShimmerLibrary{
    const val shimmerStdLib = "com.facebook.shimmer:shimmer:$shimmerVer"
}

object PagingLibrary{
    const val pagingStdLib = "android.arch.paging:runtime:$pagingRuntimeVer"
}

object WorkerLibrary{
    const val workManagerStdLib = "androidx.work:work-runtime-ktx:$workerVersion"
}

object FirebaseLibraries{
    const val gmsServiceStdLib = "com.google.gms:google-services:$gmsServiceVer"
    const val firebaseCoreStdLib = "com.google.firebase:firebase-core:$firebaseCoreVer"
    const val firebaseAuthStdLib = "com.google.firebase:firebase-auth:$firebaseAuthVer"
    const val firebaseUiAuthStdLib = "com.firebaseui:firebase-ui-auth:$firebaseAuthUiVer"
    const val facebookSdkStdLib = "com.facebook.android:facebook-android-sdk:$facebookSdkVer"
    const val facebookLoginSdkStdLib = "com.facebook.android:facebook-login:$facebookSdkVer"
    const val firebaseStorageStdLib = "com.google.firebase:firebase-storage:$firebaseStorageVer"
    const val firebaseDatabaseStdLib = "com.google.firebase:firebase-database:$firebaseDatabaseVer"
}
