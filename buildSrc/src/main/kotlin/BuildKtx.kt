import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.jvm.toolchain.JavaToolchainSpec
import org.gradle.kotlin.dsl.project

/**
 *  Util functions for adding the different type dependencies from build.gradle.kts file
 */

internal fun DependencyHandler.implementation(dependency: String) {
    add("implementation", dependency)
}

internal fun DependencyHandler.debugImplementation(dependency: String) {
    add("debugImplementation", dependency)
}

internal fun DependencyHandler.kapt(dependency: String) {
    add("kapt", dependency)
}

internal fun DependencyHandler.api(dependency: String) {
    add("api", dependency)
}

internal fun DependencyHandler.implementationModule(module: String) {
    add("implementation", project(module))
}

internal fun DependencyHandler.apiModule(module: String) {
    add("api", project(module))
}

internal fun DependencyHandler.implementationPlatform(dependency: String) {
    add("implementation", platform(dependency))
}

internal fun DependencyHandler.androidTestImplementation(dependency: String) {
    add("androidTestImplementation", dependency)
}

internal fun DependencyHandler.testImplementation(dependency: String) {
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
