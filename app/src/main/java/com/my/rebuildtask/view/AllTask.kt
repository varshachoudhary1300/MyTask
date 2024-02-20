package com.my.rebuildtask.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.my.rebuildtask.R
import com.my.rebuildtask.adapters.TaskAdapter
import com.my.rebuildtask.databinding.AllTaskBinding
import com.my.rebuildtask.model.customOrder
import com.my.rebuildtask.viewModel.TasksVM

class AllTask : Fragment(R.layout.all_task) {

    private lateinit var binding: AllTaskBinding
    private lateinit var allTaskAdapter : TaskAdapter
    private val viewModel by activityViewModels<TasksVM>()

   /**
    * On View Created
    * */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AllTaskBinding.bind(view)
        clickHandler()
        allTaskAdapter = TaskAdapter {
            view.findNavController().navigate(AllTaskDirections.actionAllTaskToAddTask(it))
        }
        val tasks = viewModel.getAllTasks().sortedWith(compareBy { customOrder.indexOf(it.priority) })
        allTaskAdapter.submitList(tasks)
        if (tasks.isEmpty()){
            // visibility of No Data found textview
            binding.tvNoDataFound.visibility = View.VISIBLE
            binding.rvTask.visibility = View.INVISIBLE
        } else {
            binding.tvNoDataFound.visibility = View.INVISIBLE
            binding.rvTask.visibility = View.VISIBLE
        }
        setAdapter()
    }


    /**
     * Set Adapter
     * */
    private fun setAdapter(){
        binding.rvTask.adapter = allTaskAdapter
    }

    /**
     * Click Handler
     * */
    private fun clickHandler(){
        binding.btAdd.setOnClickListener {
            it.findNavController().navigate(R.id.addTask)
        }
    }
}