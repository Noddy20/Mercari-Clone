plugins {
    pluginJavaLibrary()
    pluginKotlinJVM()
    pluginModuleKotlinxSerialization()
}

kotlin.jvmToolchain {
    applyKotlinCompatibility()
}

java {
    applyJavaCompatibility()
}

dependencies {
    implementKotlinxSerialization()
}