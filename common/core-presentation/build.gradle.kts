plugins {
    alias(libs.plugins.moduleSetup)
}

android {
    namespace = "kmp.multimodule.sample.common.core.presentation"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.bundles.decompose.presentation)
                api(libs.koin.core)
                api(libs.coroutines.core)
            }
        }
    }
}