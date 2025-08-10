package kmp.multimodule.sample.plugins

import com.android.build.gradle.LibraryExtension
import kmp.multimodule.sample.common.configureAndroid
import kmp.multimodule.sample.common.configureKotlinMultiplatform
import kmp.multimodule.sample.common.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ModuleSetupPlugin : Plugin<Project> {
    override fun apply(project: Project) = project.run {
        with(pluginManager) {
            apply(libs.findPlugin("androidLibrary").get().get().pluginId)
            apply(libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
            apply(libs.findPlugin("serialization").get().get().pluginId)
        }

        extensions.configure<KotlinMultiplatformExtension>(::configureKotlinMultiplatform)
        extensions.configure<LibraryExtension>(::configureAndroid)
    }
}