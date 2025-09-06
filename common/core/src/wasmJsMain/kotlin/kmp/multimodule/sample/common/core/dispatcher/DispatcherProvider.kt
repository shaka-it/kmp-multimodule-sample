package kmp.multimodule.sample.common.core.dispatcher

import kotlinx.coroutines.Dispatchers

actual fun provideIoDispatcher() = Dispatchers.Default