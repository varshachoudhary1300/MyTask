package com.my.rebuildtask.controller

import android.app.Application
import com.my.rebuildtask.prefs.MySharedPreferences

class MyApplication : Application() {

    companion object {
        var prefInstance : MySharedPreferences? = null
    }


    override fun onCreate() {
        super.onCreate()
        prefInstance = MySharedPreferences(applicationContext)
    }

}