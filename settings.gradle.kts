pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Mercari Clone"
include(":app")

include(":model:presentation:shared")
include(":presentation:home-container")
include(":presentation:home-catalogue")

include(":domain:shared:api")
include(":domain:product-catalogue:api", ":domain:product-catalogue:implementation")

include(":model:data:shared")
include(":model:data:product-catalogue")
include(":data:product-catalogue:api", ":data:product-catalogue:implementation")

include(":core:design-system")
include(":core:resources")
include(":core:logger")
include(":core:utils:multithreading:api", ":core:utils:multithreading:implementation")
include(":core:ktx:kotlin-ktx", ":core:ktx:core-ktx", ":core:ktx:android-ktx")
include(":core:utils:dependency-injection")
include(":core:network:network-client:api", ":core:network:network-client:implementation")
include(":model:domain:shared")
include(":model:domain:product-catalogue")
include(":core:utils:image-loader")
include(":presentation:shared:api")
include(":presentation:shared:implementation")
include(":presentation:splash")
include(":presentation:navigation:implementation")
include(":presentation:navigation:api")
include(":core:utils:test-utils")
