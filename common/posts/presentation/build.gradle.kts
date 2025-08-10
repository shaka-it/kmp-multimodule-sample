plugins {
    alias(libs.plugins.moduleSetup)
}

android {
    namespace = "kmp.multimodule.sample.common.posts.presentation"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.posts.api)
            implementation(projects.common.corePresentation)
        }
    }
}