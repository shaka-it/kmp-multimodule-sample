plugins {
    alias(libs.plugins.moduleSetup)
}

android {
    namespace ="kmp.multimodule.sample.common.root.presentation"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.common.corePresentation)
                implementation(projects.common.auth.api)
                implementation(projects.common.auth.presentation)
                implementation(projects.common.main.presentation)
            }
        }
    }
}