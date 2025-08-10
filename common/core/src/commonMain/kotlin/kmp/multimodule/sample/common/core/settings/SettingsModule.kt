package kmp.multimodule.sample.common.core.settings

import com.russhwolf.settings.Settings
import org.koin.dsl.module

internal val settingsModule = module {
    single { Settings() }
}