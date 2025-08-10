plugins {
    alias(libs.plugins.moduleSetup)
    alias(libs.plugins.composeSetup)
}

android {
    namespace ="kmp.multimodule.sample.common.root"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.common.coreCompose)
                implementation(projects.common.root.presentation)
                implementation(projects.common.main.compose)
                implementation(projects.common.main.presentation)
                implementation(projects.common.auth.compose)
                implementation(projects.common.auth.presentation)
            }
        }
    }
}