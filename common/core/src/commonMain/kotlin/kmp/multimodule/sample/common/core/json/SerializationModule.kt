package kmp.multimodule.sample.common.core.json

import kotlinx.serialization.json.Json
import org.koin.dsl.module

internal val serializationModule = module {
    single {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }
}