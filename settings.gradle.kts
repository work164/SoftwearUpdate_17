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
include(":adsdk")
