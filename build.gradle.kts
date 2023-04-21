buildscript {
    repositories {
        google()
    }
}
plugins {
    pluginAndroidApplication(apply = false)
    pluginAndroidLibrary(apply = false)
    pluginKotlinJVM(apply = false)
    pluginAndroidKotlin(apply = false)
    pluginProjectKotlinxSerialization()
    pluginProjectHiltAndroid()
}