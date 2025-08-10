plugins {
    alias(libs.plugins.moduleSetup)
}

android {
    namespace = "kmp.multimodule.sample.common.profile.presentation"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.auth.api)
            implementation(projects.common.corePresentation)
        }
    }
}