package kmp.multimodule.sample

import kmp.multimodule.sample.common.core.PlatformConfiguration
import android.app.Application
import kmp.multimodule.sample.common.umbrella.core.PlatformSDK

class KmpSampleApp: Application() {

    override fun onCreate() {
        super.onCreate()

        initPlatformSDK()
    }
}

fun KmpSampleApp.initPlatformSDK() =
    PlatformSDK.init(configuration = PlatformConfiguration(androidContext = applicationContext))