package kmp.multimodule.sample.common

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinMultiplatform(
    extension: KotlinMultiplatformExtension,
) = extension.apply {

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        binaries.executable()
        browser()
    }

    jvm("desktop")

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    androidTarget()

    jvmToolchain(21)
}