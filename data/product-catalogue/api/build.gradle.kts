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
    apiModuleModelDataProductCatalogue()
    apiModuleModelDataShared()
}