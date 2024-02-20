package com.my.rebuildtask.prefs

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.my.rebuildtask.model.Task

class MySharedPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    /**
     * Save Data into preference
     * */
    fun saveData(list: List<Task>) {
        val editor = sharedPreferences.edit()
        editor.putString(SharedPreferenceKeys.TASKS_LIST, Gson().toJson(list))
        editor.apply()
    }

    /**
     * Load All tasks
     * */
    fun loadAllTasks(): List<Task> {
        val data = sharedPreferences.getString(SharedPreferenceKeys.TASKS_LIST, "[]")
        val type = object : TypeToken<List<Task>?>() {}.type
        return Gson().fromJson(data, type)
    }
}


object SharedPreferenceKeys {
    const val TASKS_LIST = "tasksList"
}