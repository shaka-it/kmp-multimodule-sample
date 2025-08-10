package kmp.multimodule.sample.common.core.presentation.component

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

/**
 * Used to create Decompose components, creation of components are implemented as extension functions
 */
class ComponentFactory(private val localKoin: Koin) : KoinComponent {

    override fun getKoin(): Koin = localKoin
}