package kmp.multimodule.sample.common.umbrella.core

import kmp.multimodule.sample.common.auth.data.authDataModule
import kmp.multimodule.sample.common.core.PlatformConfiguration
import kmp.multimodule.sample.common.core.coreModule
import kmp.multimodule.sample.common.core.di.Inject
import kmp.multimodule.sample.common.core.presentation.corePresentationModule
import kmp.multimodule.sample.common.posts.data.postsDataModule
import kmp.multimodule.sample.common.posts.presentation.postsPresentationModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

object PlatformSDK {
    fun init(
        configuration: PlatformConfiguration
    ) {
        val umbrellaModule = module {
            single {
                configuration
            }
        }
        val modules = listOf(
            umbrellaModule,
            coreModule,
            corePresentationModule,
            postsDataModule,
            authDataModule,
            postsPresentationModule,
        )

        Inject.init(
            koin = startKoin {
                modules(modules = modules)
            }.koin,
        )
    }
}