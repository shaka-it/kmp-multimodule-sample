rootProject.name = "kmp-multimodule-sample"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":server")
include(":composeApp")
include(":kobweb-site")
include(":common:auth:api")
include(":common:auth:data")
include(":common:auth:compose")
include(":common:auth:presentation")
include(":common:posts:api")
include(":common:posts:data")
include(":common:posts:compose")
include(":common:posts:presentation")
include(":common:profile:compose")
include(":common:profile:presentation")
include(":common:main:compose")
include(":common:main:presentation")
include(":common:core")
include(":common:core-compose")
include(":common:core-presentation")
include(":common:umbrella")
include(":common:root:compose")
include(":common:root:presentation")
include(":common:demo")