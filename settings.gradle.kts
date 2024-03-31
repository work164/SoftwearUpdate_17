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
