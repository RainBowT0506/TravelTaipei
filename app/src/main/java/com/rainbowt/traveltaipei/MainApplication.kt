package com.rainbowt.traveltaipei

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import timber.log.Timber


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            Logger.addLogAdapter(AndroidLogAdapter())
            Timber.plant(CustomDebugTree())
        }
    }




    class CustomDebugTree() : Timber.DebugTree() {
        override fun createStackElementTag(element: StackTraceElement): String {
            return String.format(
                "Timber [L:%s] [M:%s] [C:%s]",
                element.lineNumber,
                element.methodName,
                super.createStackElementTag(element)
            )
        }
    }
}

