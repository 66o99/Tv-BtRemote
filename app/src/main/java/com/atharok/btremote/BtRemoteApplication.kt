package com.atharok.btremote

import android.app.Application
import com.atharok.btremote.common.injections.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BtRemoteApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BtRemoteApplication)
            modules(appModules)
        }
    }
}