package kmp.multimodule.sample.common

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project

internal fun Project.configureAndroid(
    extension: LibraryExtension,
) = extension.apply {
    compileSdk = libs.findVersion("android.compileSdk").get().requiredVersion.toInt()
}