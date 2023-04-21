plugins {
    pluginJavaLibrary()
    pluginKotlinJVM()
}

kotlin.jvmToolchain {
    applyKotlinCompatibility()
}

java {
    applyJavaCompatibility()
}

dependencies {
    apiModuleDomainSharedApi()
    apiModuleModelDomainShared()
    apiModuleModelDomainProductCatalogue()
}