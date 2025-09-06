package kmp.multimodule.sample.common.auth.data

import kmp.multimodule.sample.common.auth.api.AuthRepository
import kmp.multimodule.sample.common.auth.data.ktor.KtorAuthRemoteDataSource
import kmp.multimodule.sample.common.auth.data.settings.SettingsAuthDataSource
import kmp.multimodule.sample.common.core.dispatcher.provideIoDispatcher
import org.koin.dsl.module

val authDataModule = module {
    single<AuthRepository> {
        DefaultAuthRepository(
            remoteDataSource = get(),
            cacheDataSource = get(),
            ioDispatcher = provideIoDispatcher(),
        )
    }

    factory {
        SettingsAuthDataSource(settings = get())
    }

    factory {
        KtorAuthRemoteDataSource(httpClient = get())
    }
}