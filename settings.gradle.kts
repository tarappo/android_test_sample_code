pluginManagement {
    repositories {
        // Google を先頭に
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            val id = requested.id.id
            if (id == "com.android.application" ||
                id == "com.android.library" ||
                id == "com.android.test" ||
                id == "com.android.dynamic-feature") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AndroidTestSampleCode"
include(":app")
