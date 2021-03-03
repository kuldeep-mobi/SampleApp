package com.mobikasa.logisticapp.utils

import android.app.Application
import android.content.Context

class LogisticApp : Application() {

    companion object {
        private var mLogisticApp: LogisticApp? = null
        fun applicationContext(): Context {
            return mLogisticApp!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        mLogisticApp = this
    }
}