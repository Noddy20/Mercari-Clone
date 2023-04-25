import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 *   Dependencies
 */

/**
 *   -- Core --
 */
// Core Ktx
fun DependencyHandler.implementCoreKtx() {
    implementation("androidx.core:core-ktx:$VERSION_CORE_KTX")
}

/**
 *   -- Lifecycle --
 */

// Liecycle RunTime
fun DependencyHandler.implementLifecycleRunTime() {
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$VERSION_LIFECYCLE")
}

// Lifecycle ViewModel Compose
fun DependencyHandler.implementLifecycleViewModelCompose() {
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$VERSION_LIFECYCLE")
}

// ViewMode SavedState
fun DependencyHandler.implementLifecycleViewModelSavedState() {
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$VERSION_LIFECYCLE")
}

// Lifecycle Process
fun DependencyHandler.implementLifecycleProcess() {
    implementation("androidx.lifecycle:lifecycle-process:$VERSION_LIFECYCLE")
}

// Lifecycle Service
fun DependencyHandler.implementLifecycleService() {
    implementation("androidx.lifecycle:lifecycle-service:$VERSION_LIFECYCLE")
}

/**
 *   -- UI & Compose --
 */

// Activity Compose
fun DependencyHandler.implementActivityCompose() {
    implementation("androidx.activity:activity-compose:$VERSION_ACTIVITY_COMPOSE")
}

// Splash Api
fun DependencyHandler.implementSplash() {
    implementation("androidx.core:core-splashscreen:$VERSION_SPLASH_API")
}

// Compose UI
fun DependencyHandler.implementComposeUi() {
    implementation("androidx.compose.ui:ui:$VERSION_COMPOSE")
}

// Compose UI Tooling Preview
fun DependencyHandler.implementComposeUiToolingPreview() {
    implementation("androidx.compose.ui:ui-tooling-preview:$VERSION_COMPOSE")
}

// Compose Foundation
fun DependencyHandler.implementComposeFoundation() {
    implementation("androidx.compose.foundation:foundation:$VERSION_COMPOSEFOUNDATION")
}

// Navigation Compose
/*fun DependencyHandler.implementNavigationCompose() {
    implementation("androidx.navigation:navigation-compose:$VERSION_NAVIGATION_COMPOSE")
}*/

// Compose Material
fun DependencyHandler.implementComposeMaterial() {
    implementation("androidx.compose.material:material:$VERSION_COMPOSE_MATERIAL")
}

// ConstraintLayout Compose
fun DependencyHandler.implementConstratintLayoutCompose() {
    implementation("androidx.constraintlayout:constraintlayout-compose:$VERSION_CONSTRAINTLAYOUT_COMPOSE")
}

// Lottie Compose
fun DependencyHandler.implementLottieCompose() {
    implementation("com.airbnb.android:lottie-compose:$VERSION_LOTTIE_COMPOSE")
}

/**
 *   -- DI --
 */

// Hilt Android
fun DependencyHandler.implementHiltAndroid() {
    implementation("com.google.dagger:hilt-android:$VERSION_HILT")
}

// Hilt Android Compiler
fun DependencyHandler.kaptHiltAndroidCompiler() {
    kapt("com.google.dagger:hilt-android-compiler:$VERSION_HILT")
}

// Hilt Worker
fun DependencyHandler.implementHiltWorker() {
    implementation("androidx.hilt:hilt-work:$VERSION_HILT_WORKER")
}

// Hilt Worker
fun DependencyHandler.implementHiltNavigationCompose() {
    implementation("androidx.hilt:hilt-navigation-compose:$VERSION_HILT_NAVIGATION_COMPOSE")
}

/**
 *   -- Coroutines --
 */

fun DependencyHandler.implementCoroutineCore() {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$VERSION_COROUTINES")
}

fun DependencyHandler.implementCoroutineAndroid() {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$VERSION_COROUTINES")
}

/**
 *   -- Network --
 */

fun DependencyHandler.implementKotlinxSerialization() {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$VERSION_KOTLINX_SERIALIZATION")
}

fun DependencyHandler.implementPlatformOkHttpBom() {
    implementationPlatform("com.squareup.okhttp3:okhttp-bom:$VERSION_OKHTTP3_BOM")
}

fun DependencyHandler.implementOkHttp3() {
    implementation("com.squareup.okhttp3:okhttp")
}

fun DependencyHandler.implementOkHttp3LoggingIntercepter() {
    implementation("com.squareup.okhttp3:logging-interceptor")
}

fun DependencyHandler.implementRetrofit2() {
    implementation("com.squareup.retrofit2:retrofit:$VERSION_RETROFIT2")
}

fun DependencyHandler.apiRetrofit2() {
    api("com.squareup.retrofit2:retrofit:$VERSION_RETROFIT2")
}

fun DependencyHandler.implementRetrofit2KotlinxSerializationConverter() {
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:$VERSION_RETROFIT2_KOTLINX_SERIALIZATION_CONVERTER")
}

/**
 *   Image Loader Dependencies
 */

fun DependencyHandler.implementCoilCompose() {
    implementation("io.coil-kt:coil-compose:$VERSION_COIL_COMPOSE")
}

/**
 *   Debug Dependencies
 */

// Debug Compose UI Tooling
fun DependencyHandler.debugImplementComposeUiTooling() {
    debugImplementation("androidx.compose.ui:ui-tooling:$VERSION_COMPOSE")
}

// Compose UI Test Manifest
fun DependencyHandler.debugImplementComposeUiTestManifest() {
    debugImplementation("androidx.compose.ui:ui-test-manifest:$VERSION_COMPOSE")
}

/**
 *   Test Dependencies
 */

// JUnit
fun DependencyHandler.testImplementJUnit() {
    testImplementation("junit:junit:$VERSION_J_UNIT")
}

/**
 *   Android Test Dependencies
 */

// Ext JUnit
fun DependencyHandler.androidTestImplementExtJUnit() {
    androidTestImplementation("androidx.test.ext:junit:$VERSION_EXT_J_UNIT")
}

// Espresso Core
fun DependencyHandler.androidTestImplementEspressoCore() {
    androidTestImplementation("androidx.test.espresso:espresso-core:$VERSION_ESPRESSO_CORE")
}

// Compose UI Test JUnit-4
fun DependencyHandler.androidTestImplementComposeUiTestJUnit4() {
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$VERSION_COMPOSE")
}

// Hilt Android Testing
fun DependencyHandler.androidTestImplementHiltAndroidTest() {
    androidTestImplementation("com.google.dagger:hilt-android-testing:$VERSION_HILT")
}