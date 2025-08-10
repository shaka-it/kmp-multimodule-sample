package kmp.multimodule.sample.common.core

import kmp.multimodule.sample.common.core.database.databaseModule
import kmp.multimodule.sample.common.core.json.serializationModule
import kmp.multimodule.sample.common.core.ktor.ktorModule
import kmp.multimodule.sample.common.core.settings.settingsModule
import org.koin.dsl.module

val coreModule = module {
    includes(
        ktorModule,
        serializationModule,
        databaseModule,
        settingsModule,
    )
}