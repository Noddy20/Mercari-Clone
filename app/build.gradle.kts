import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    pluginAndroidApplication()
    pluginAndroidKotlin()
    pluginModuleHiltAndroid()
    pluginKotlinKapt()
}

apply {
    pluginModuleSpotless(project.rootDir.toString())
}

android {

    /*signingConfigs {
        getByName(BuildTypes.DEBUG) {
            storeFile = rootProject.file(ProjectConfig.DEBUG_KEYSTORE)
        }
    }*/

    namespace = Config.APP_ID
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        applicationId = Config.APP_ID
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK
        versionCode = Config.APP_VERSION_CODE
        versionName = Config.APP_VERSION_NAME

        testInstrumentationRunner = Config.ANDROID_TEST_INSTRUMENTATION

        vectorDrawables.useSupportLibrary = Config.IS_VECTOR_DRAWABLES_USE_SUPPORT_LIBRARY
        multiDexEnabled = Config.IS_MULTI_DEX_ENABLED
    }
    buildTypes {
        release {
            isMinifyEnabled = Config.IS_MINIFY_ENABLED_FOR_RELEASE
            isShrinkResources = Config.IS_SHRINK_RESOURCE_ENABLED_FOR_RELEASE
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = Config.IS_MINIFY_ENABLED_FOR_DEBUG
            isShrinkResources = Config.IS_SHRINK_RESOURCE_ENABLED_FOR_DEBUG
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        // Append the version name to the end of aligned APKs
        applicationVariants.all {
            outputs.all {
                if(name.contains(Config.BUILD_TYPE_RELEASE))
                    (this as BaseVariantOutputImpl).outputFileName = "${name}-v${versionName}.apk"
            }
        }
    }

    compileOptions {
        sourceCompatibility = Config.JAVA_KOTLIN_VERSION
        targetCompatibility = Config.JAVA_KOTLIN_VERSION
    }

    kotlinOptions {
        jvmTarget = Config.JAVA_KOTLIN_VERSION_CODE.toString()
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    lint {
        abortOnError = false
        //isIgnoreWarnings = true
        //isQuiet = true
    }
}

dependencies {
    implementCoreKtx()

    implementHiltAndroid()
    kaptHiltAndroidCompiler()

    implementModulePresentationSharedImplementation()
    implementModulePresentationNavigationImplementation()
    implementModulePresentationSplash()
    implementModulePresentationHomeContainer()
    implementModulePresentationHomeCatalogue()

    implementModuleDomainProductCatalogueImplementation()

    implementModuleDataProductCatalogueImplementation()

    implementModuleCoreResources()
    implementModuleCoreUtilsMultithreadingImplementation()
    implementModuleCoreNetworkClientImplementation()

    androidTestImplementExtJUnit()
    androidTestImplementEspressoCore()
    androidTestImplementComposeUiTestJUnit4()
}