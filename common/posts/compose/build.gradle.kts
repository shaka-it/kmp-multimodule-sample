plugins {
    alias(libs.plugins.moduleSetup)
    alias(libs.plugins.composeSetup)
}

android {
    namespace = "kmp.multimodule.sample.common.posts.compose"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.coreCompose)
            implementation(projects.common.posts.presentation)
        }
    }
}