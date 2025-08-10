plugins {
    alias(libs.plugins.moduleSetup)
}

android {
    namespace = "kmp.multimodule.sample.common.posts.api"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.coroutines.core)
        }
    }
}