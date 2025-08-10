package kmp.multimodule.sample.common.core.presentation

import kmp.multimodule.sample.common.core.presentation.component.componentModule
import org.koin.dsl.module

val corePresentationModule = module {
    includes(
        componentModule,
    )
}