plugins {
    alias(libs.plugins.moduleSetup)
    alias(libs.plugins.composeSetup)
}

android {
    namespace = "kmp.multimodule.sample.common.auth.compose"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.common.coreCompose)
                implementation(projects.common.auth.presentation)
            }
        }
    }
}