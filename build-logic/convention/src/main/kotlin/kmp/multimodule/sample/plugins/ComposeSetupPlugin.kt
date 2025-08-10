package kmp.multimodule.sample.plugins

import kmp.multimodule.sample.common.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposeSetupPlugin : Plugin<Project> {
    override fun apply(project: Project) = project.run {
        with(pluginManager) {
            apply(libs.findPlugin("composeCompiler").get().get().pluginId)
            apply(libs.findPlugin("composeMultiplatform").get().get().pluginId)
            apply(libs.findPlugin("composeHotReload").get().get().pluginId)
        }

        val compose = extensions.getByType<ComposeExtension>().dependencies

        project.dependencies.apply {
            add("debugImplementation", compose.uiTooling)
        }

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                androidMain.dependencies {
                    implementation(compose.preview)
                }
                commonMain.dependencies {
                    implementation(compose.runtime)
                    implementation(compose.foundation)
                    implementation(compose.material)
                    implementation(compose.material3)
                    implementation(compose.ui)
                    implementation(compose.components.resources)
                    implementation(compose.components.uiToolingPreview)
                    implementation(compose.materialIconsExtended)

                    implementation(libs.findBundle("decompose-compose").get())
                }
            }
        }
    }
}