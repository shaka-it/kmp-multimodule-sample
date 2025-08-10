plugins {
    alias(libs.plugins.moduleSetup)
}

android {
    namespace = "kmp.multimodule.sample.common.main.presentation"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.corePresentation)
            implementation(projects.common.posts.presentation)
            implementation(projects.common.profile.presentation)
        }
    }
}