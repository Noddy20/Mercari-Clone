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
    implementCoroutineCore()
    apiModuleModelDomainShared()
    apiModuleModelDataShared()

    testImplementTestJunit()
}