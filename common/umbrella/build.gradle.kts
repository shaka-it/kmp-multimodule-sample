plugins {
    alias(libs.plugins.moduleSetup)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.common.core)
                implementation(projects.common.corePresentation)
                implementation(projects.common.auth.data)
                implementation(projects.common.posts.data)
                implementation(projects.common.posts.presentation)
            }
        }
    }
}

android {
    namespace ="kmp.multimodule.sample.common.umbrella.core"
}