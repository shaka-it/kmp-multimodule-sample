plugins {
    alias(libs.plugins.moduleSetup)
}

android {
    namespace = "kmp.multimodule.sample.common.auth.data"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.common.auth.api)
                implementation(projects.common.core)

                implementation(libs.multiplatform.settings.core)
            }
        }
    }
}