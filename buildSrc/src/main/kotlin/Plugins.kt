import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec

/**
 *   Plugins
 */

fun PluginDependenciesSpec.pluginAndroidApplication(apply: Boolean = true) {
    id("com.android.application") version (VERSION_AGP) apply(apply)
}

fun PluginDependenciesSpec.pluginAndroidLibrary(apply: Boolean = true) {
    id("com.android.library") version (VERSION_AGP) apply(apply)
}

fun PluginDependenciesSpec.pluginJavaLibrary() {
    id("java-library")
}

fun PluginDependenciesSpec.pluginKotlinJVM(apply: Boolean = true) {
    id("org.jetbrains.kotlin.jvm") version (VERSION_KOTLIN) apply (apply)
}

fun PluginDependenciesSpec.pluginAndroidKotlin(apply: Boolean = true) {
    id("org.jetbrains.kotlin.android") version (VERSION_KOTLIN) apply(apply)
}

fun PluginDependenciesSpec.pluginProjectHiltAndroid() {
    id("com.google.dagger.hilt.android") version (VERSION_HILT) apply(false)
}

fun PluginDependenciesSpec.pluginProjectKotlinxSerialization() {
    id("org.jetbrains.kotlin.plugin.serialization") version (VERSION_KOTLINX_SERIALIZATION_PLUGIN) apply(false)
}

fun PluginDependenciesSpec.pluginModuleKotlinxSerialization() {
    id("kotlinx-serialization")
}

fun PluginDependenciesSpec.pluginModuleHiltAndroid() {
    id("dagger.hilt.android.plugin")
}

fun PluginDependenciesSpec.pluginKotlinParcelize() {
    id("kotlin-parcelize")
}

fun PluginDependenciesSpec.pluginKotlinKapt() {
    id("kotlin-kapt")
}