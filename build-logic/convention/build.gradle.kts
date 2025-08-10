plugins {
    `kotlin-dsl`
}

group = "kmp.multimodule.sample.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    compileOnly(libs.plugin.android)
    compileOnly(libs.plugin.kotlin)
    compileOnly(libs.plugin.compose)
    compileOnly(libs.plugin.sqldelight)
    implementation(libs.plugin.serialization)
}

gradlePlugin {
    plugins {
        create("moduleSetup") {
            id = "module.setup"
            implementationClass = "kmp.multimodule.sample.plugins.ModuleSetupPlugin"
        }
        create("composeSetup") {
            id = "compose.setup"
            implementationClass = "kmp.multimodule.sample.plugins.ComposeSetupPlugin"
        }
    }
}