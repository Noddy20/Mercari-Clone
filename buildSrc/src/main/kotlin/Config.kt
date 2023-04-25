import org.gradle.api.JavaVersion

object Config {

    const val APP_NAME = "Mercari"
    const val APP_ID = "com.example.mercari"
    const val APP_VERSION_CODE = 1
    const val APP_VERSION_NAME = "1.0.0"

    const val MIN_SDK = 24
    const val COMPILE_SDK = 33
    const val TARGET_SDK = 33

    val JAVA_KOTLIN_VERSION_CODE = 17//JavaVersion.VERSION_17
    val JAVA_KOTLIN_VERSION = JavaVersion.VERSION_17

    const val ANDROID_TEST_INSTRUMENTATION = "androidx.test.runner.AndroidJUnitRunner"

    const val IS_VECTOR_DRAWABLES_USE_SUPPORT_LIBRARY = true
    const val IS_MULTI_DEX_ENABLED = false

    const val BUILD_TYPE_RELEASE = "release"
    const val BUILD_TYPE_DEBUG = "debug"

    const val IS_MINIFY_ENABLED_FOR_RELEASE = true
    const val IS_SHRINK_RESOURCE_ENABLED_FOR_RELEASE = true

    const val IS_MINIFY_ENABLED_FOR_DEBUG = false
    const val IS_SHRINK_RESOURCE_ENABLED_FOR_DEBUG = false

    const val BASE_URL = "https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/"
}