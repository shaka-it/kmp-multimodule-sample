plugins {
    alias(libs.plugins.moduleSetup)
}

kotlin {
    js(IR) { browser() }
}

android {
    namespace ="kmp.multimodule.sample.common.demo"
}