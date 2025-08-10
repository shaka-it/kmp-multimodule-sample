plugins {
    alias(libs.plugins.moduleSetup)
}

android {
    namespace = "kmp.multimodule.sample.common.posts.data"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.sqldelight.coroutines)
            api(projects.common.auth.api)
            api(projects.common.posts.api)
            implementation(projects.common.core)
        }
    }
}