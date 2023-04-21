import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.jvm.toolchain.JavaToolchainSpec
import org.gradle.kotlin.dsl.project

/**
 *  Util functions for adding the different type dependencies from build.gradle.kts file
 */

fun DependencyHandler.implementation(dependency: String) {
    add("implementation", dependency)
}

fun DependencyHandler.debugImplementation(dependency: String) {
    add("debugImplementation", dependency)
}

fun DependencyHandler.kapt(dependency: String) {
    add("kapt", dependency)
}

fun DependencyHandler.api(dependency: String) {
    add("api", dependency)
}

fun DependencyHandler.implementationModule(module: String) {
    add("implementation", project(module))
}

fun DependencyHandler.apiModule(module: String) {
    add("api", project(module))
}

fun DependencyHandler.implementationPlatform(dependency: String) {
    add("implementation", platform(dependency))
}

fun DependencyHandler.androidTestImplementation(dependency: String) {
    add("androidTestImplementation", dependency)
}

fun DependencyHandler.testImplementation(dependency: String) {
    add("testImplementation", dependency)
}


/**
 *   Other Gradle
 */

fun JavaPluginExtension.applyJavaCompatibility() {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(Config.JAVA_KOTLIN_VERSION_CODE))
    }
}

fun JavaToolchainSpec.applyKotlinCompatibility() {
    languageVersion.set(JavaLanguageVersion.of(Config.JAVA_KOTLIN_VERSION_CODE))
}
