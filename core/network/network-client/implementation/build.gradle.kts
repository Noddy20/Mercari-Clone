plugins {
    pluginAndroidLibrary()
    pluginAndroidKotlin()
    pluginModuleHiltAndroid()
    pluginModuleKotlinxSerialization()
    pluginKotlinKapt()
}

apply {
    pluginModuleSpotless(project.rootDir.toString())
}

android {
    namespace = "com.network.networkclient"
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK

        testInstrumentationRunner = Config.ANDROID_TEST_INSTRUMENTATION
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField(
            "String",
            "BASE_URL",
            "\"" + Config.BASE_URL + "\""
        )
    }

    buildFeatures {
        buildConfig = true
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
}

dependencies {
    implementCoreKtx()

    implementHiltAndroid()
    kaptHiltAndroidCompiler()

    implementCoroutineCore()

    implementPlatformOkHttpBom()
    implementOkHttp3()
    implementOkHttp3LoggingIntercepter()
    implementRetrofit2()
    implementRetrofit2KotlinxSerializationConverter()
    implementKotlinxSerialization()

    implementModuleCoreLogger()
    implementModuleCoreKtx()
    implementModuleCoreUtilsMultithreadingApi()
    implementModuleCoreUtilsDependencyInjection()
    implementModuleCoreNetworkClientApi()

    testImplementModuleTestUtils()
}