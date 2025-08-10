plugins {
    alias(libs.plugins.moduleSetup)
    alias(libs.plugins.composeSetup)
}

android {
    namespace = "kmp.multimodule.sample.common.main.compose"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.common.coreCompose)
                implementation(projects.common.main.presentation)
                implementation(projects.common.profile.compose)
                implementation(projects.common.profile.presentation)
                implementation(projects.common.posts.compose)
                implementation(projects.common.posts.presentation)
            }
        }
    }
}