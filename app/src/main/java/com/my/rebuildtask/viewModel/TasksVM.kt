package com.my.rebuildtask.viewModel

import androidx.lifecycle.ViewModel
import com.my.rebuildtask.controller.MyApplication
import com.my.rebuildtask.model.Task

class TasksVM : ViewModel() {

    private val taskList by lazy { ArrayList<Task>() }


    fun getAllTasks(): List<Task>{
        MyApplication.prefInstance?.let {
            taskList.clear()
            taskList.addAll(it.loadAllTasks())
            return taskList
        } ?: run { return emptyList() }
    }


    fun updateData(task: Task){
        MyApplication.prefInstance?.let {
            taskList.find { it.id == task.id }?.let {
                val index = taskList.indexOf(it)
                taskList[index] = task
            }
            it.saveData(taskList)
        }
    }

    fun addTask(task: Task){
        MyApplication.prefInstance?.let {
            taskList.add(task)
            it.saveData(taskList)
        }
    }

}