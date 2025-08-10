plugins {
    alias(libs.plugins.moduleSetup)
    alias(libs.plugins.composeSetup)
}

android {
    namespace = "kmp.multimodule.sample.common.profile.compose"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.profile.presentation)
            implementation(projects.common.coreCompose)

            implementation(libs.image.loader.core)
            implementation(libs.image.loader.compose)
        }
    }
}