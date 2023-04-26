plugins {
    pluginAndroidLibrary()
    pluginAndroidKotlin()
    pluginKotlinKapt()
    pluginModuleHiltAndroid()
}

android {
    namespace = "com.mercari.data.productcatalogue"
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
            isMinifyEnabled = Config.IS_MINIFY_ENABLED_FOR_DEBUG
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

    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    implementCoreKtx()

    implementHiltAndroid()
    kaptHiltAndroidCompiler()

    implementModuleCoreLogger()
    implementModuleCoreKtx()
    implementModuleCoreUtilsMultithreadingApi()
    implementModuleCoreUtilsDependencyInjection()
    implementModuleCoreNetworkClientApi()

    implementModuleDataProductCatalogueApi()

    testImplementModuleTestUtils()
}