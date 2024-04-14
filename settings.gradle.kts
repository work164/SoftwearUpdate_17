pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }

        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/integers1994/Ads")

            credentials {
                username = "integers1994"
                password = "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"
            }
        }


    }
}

rootProject.name = "NewSoftware"
include(":app")
//include(":adsdk")
