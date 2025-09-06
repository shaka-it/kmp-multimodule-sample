package kmp.multimodule.sample.common.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

expect fun provideIoDispatcher(): CoroutineDispatcher