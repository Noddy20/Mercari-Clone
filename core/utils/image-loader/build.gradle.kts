plugins {
    pluginAndroidLibrary()
    pluginAndroidKotlin()
}

android {
    namespace = "com.utils.imageloader"
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK

        testInstrumentationRunner = Config.ANDROID_TEST_INSTRUMENTATION
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = Config.IS_MINIFY_ENABLED_FOR_RELEASE
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Config.JAVA_KOTLIN_VERSION
        targetCompatibility = Config.JAVA_KOTLIN_VERSION
    }

    kotlinOptions {
        jvmTarget = Config.JAVA_KOTLIN_VERSION_CODE.toString()
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = VERSION_COMPOSE
    }
}

dependencies {
    implementCoreKtx()

    implementCoroutineCore()
    implementCoroutineAndroid()

    implementComposeUi()
    implementCoilCompose()

    implementModuleCoreLogger()
    implementModuleCoreKtx()
}