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
    apiRetrofit2()
}