package com.sanjib.koin.mvvmdemo.application

import androidx.multidex.MultiDexApplication
import com.sanjib.koin.mvvmdemo.di.appModule
import com.sanjib.koin.mvvmdemo.di.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppClass : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()


        val moduleList = listOf(appModule, remoteModule)
        startKoin {
            androidContext(this@AppClass)
            modules(moduleList)
        }

        appInstance = this
    }
    override fun onTerminate() {
        super.onTerminate()
        if (appInstance != null) {
            appInstance = null
        }
    }
    companion object {
        @JvmField
        var appInstance: AppClass? = null

        @JvmStatic
        fun getAppContext(): AppClass {
            return appInstance as AppClass
        }
    }
}