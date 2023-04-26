import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 *   Core Modules
 */

fun DependencyHandler.implementModuleCoreDesignSystem() {
    implementationModule(":core:design-system")
}

fun DependencyHandler.implementModuleCoreResources() {
    implementationModule(":core:resources")
}

fun DependencyHandler.implementModuleCoreLogger() {
    implementationModule(":core:logger")
}

fun DependencyHandler.implementModuleCoreUtilsMultithreadingApi() {
    implementationModule(":core:utils:multithreading:api")
}

fun DependencyHandler.implementModuleCoreUtilsMultithreadingImplementation() {
    implementationModule(":core:utils:multithreading:implementation")
}

fun DependencyHandler.implementModuleCoreUtilsImageLoader() {
    implementationModule(":core:utils:image-loader")
}

fun DependencyHandler.implementModuleCoreStoragePreferencesApi() {
    implementationModule(":core:storage:preferences:api")
}

fun DependencyHandler.implementModuleCoreStoragePreferencesImplementation() {
    implementationModule(":core:storage:preferences:implementation")
}

fun DependencyHandler.implementModuleCoreKtxKotlinKtx() {
    implementationModule(":core:ktx:kotlin-ktx")
}

fun DependencyHandler.apiModuleCoreKtxKotlinKtx() {
    apiModule(":core:ktx:kotlin-ktx")
}

fun DependencyHandler.implementModuleCoreKtx() {
    implementationModule(":core:ktx:core-ktx")
}

fun DependencyHandler.apiModuleCoreKtx() {
    apiModule(":core:ktx:core-ktx")
}

fun DependencyHandler.implementModuleCoreKtxAndroidKtx() {
    implementationModule(":core:ktx:android-ktx")
}

fun DependencyHandler.implementModuleCoreUtilsDependencyInjection() {
    implementationModule(":core:utils:dependency-injection")
}

fun DependencyHandler.implementModuleCoreNetworkClientImplementation() {
    implementationModule(":core:network:network-client:implementation")
}

fun DependencyHandler.implementModuleCoreNetworkClientApi() {
    implementationModule(":core:network:network-client:api")
}

/**
 *   Data Layer Modules
 */

fun DependencyHandler.apiModuleModelDataShared() {
    apiModule(":model:data:shared")
}

fun DependencyHandler.apiModuleModelDataProductCatalogue() {
    apiModule(":model:data:product-catalogue")
}

fun DependencyHandler.implementModuleDataProductCatalogueApi() {
    implementationModule(":data:product-catalogue:api")
}

fun DependencyHandler.implementModuleDataProductCatalogueImplementation() {
    implementationModule(":data:product-catalogue:implementation")
}

/**
 *   Domain Layer Modules
 */

fun DependencyHandler.apiModuleModelDomainShared() {
    apiModule(":model:domain:shared")
}

fun DependencyHandler.apiModuleModelDomainProductCatalogue() {
    apiModule(":model:domain:product-catalogue")
}

fun DependencyHandler.apiModuleDomainSharedApi() {
    apiModule(":domain:shared:api")
}

fun DependencyHandler.implementModuleDomainProductCatalogueApi() {
    implementationModule(":domain:product-catalogue:api")
}

fun DependencyHandler.implementModuleDomainProductCatalogueImplementation() {
    implementationModule(":domain:product-catalogue:implementation")
}


/**
 *   Presentation Layer Modules
 */

fun DependencyHandler.implementModuleModelPresentationShared() {
    implementationModule(":model:presentation:shared")
}

fun DependencyHandler.implementModulePresentationSharedApi() {
    implementationModule(":presentation:shared:api")
}

fun DependencyHandler.implementModulePresentationSharedImplementation() {
    implementationModule(":presentation:shared:implementation")
}

fun DependencyHandler.implementModulePresentationNavigationApi() {
    implementationModule(":presentation:navigation:api")
}

fun DependencyHandler.implementModulePresentationNavigationImplementation() {
    implementationModule(":presentation:navigation:implementation")
}

fun DependencyHandler.implementModulePresentationSplash() {
    implementationModule(":presentation:splash")
}

fun DependencyHandler.implementModulePresentationHomeContainer() {
    implementationModule(":presentation:home-container")
}

fun DependencyHandler.implementModulePresentationHomeCatalogue() {
    implementationModule(":presentation:home-catalogue")
}

/**
 *    Test Dependencies
 */

fun DependencyHandler.testImplementModuleTestUtils() {
    testImplementationModule(":core:utils:test-utils")
}