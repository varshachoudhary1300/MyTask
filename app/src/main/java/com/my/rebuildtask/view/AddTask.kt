package com.my.rebuildtask.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.my.rebuildtask.R
import com.my.rebuildtask.databinding.AddTaskBinding
import com.my.rebuildtask.model.Task
import com.my.rebuildtask.prefs.MySharedPreferences
import com.my.rebuildtask.viewModel.TasksVM

class AddTask : Fragment(R.layout.add_task) {

    private lateinit var binding : AddTaskBinding
    private lateinit var sharedPreferences: MySharedPreferences
    private val navArgs by lazy { navArgs<AddTaskArgs>() }
    private val viewModel by activityViewModels<TasksVM>()


    /**
     * On View Created
     * */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AddTaskBinding.bind(view)
        sharedPreferences = MySharedPreferences(requireContext())
        val spinner: Spinner = binding.etPriority
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(), R.array.dropdown_values, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        setUpData()
        clickHandler()
    }

    /**
     * Set Data into the fields
     * */
    private fun setUpData() {
        navArgs.value.taskModel?.let {
            binding.etTitle.setText(it.title)
            binding.etDescription.setText(it.description)

            val adapter = binding.etPriority.adapter as? ArrayAdapter<String>
            val priority = it.priority
            adapter?.let {
                val position = it.getPosition(priority)
                if (position != -1) {
                    binding.etPriority.setSelection(position)
                }}

        }
    }


    /**
     * Click Handler
     * */
    private fun clickHandler(){
            binding.btSubmit.setOnClickListener {

                if (binding.etTitle.text.isNullOrEmpty()||binding.etDescription.text.isNullOrEmpty()){
                    Toast.makeText(requireContext(),"Please enter all the values before submitting", Toast.LENGTH_SHORT).show()
                }else{
                    navArgs.value.taskModel?.let {
                        viewModel.updateData(Task(
                            id = it.id,
                            title = binding.etTitle.text.toString(),
                            description = binding.etDescription.text.toString(),
                            priority = binding.etPriority.selectedItem.toString()
                        ))
                    } ?: run {
                        viewModel.addTask(Task(
                            id = System.currentTimeMillis(),
                            title = binding.etTitle.text.toString(),
                            description = binding.etDescription.text.toString(),
                            priority = binding.etPriority.selectedItem.toString()
                        ))
                    }

                    Toast.makeText(requireContext(),"Data Saved successfully!", Toast.LENGTH_SHORT).show()
                    view?.findNavController()?.navigateUp()
                }

            }

//            navArgs.value.taskModel?.let {
//                viewModel.updateData(Task(
//                    id = it.id,
//                    title = binding.etTitle.text.toString(),
//                    description = binding.etDescription.text.toString(),
//                    priority = binding.etPriority.selectedItem.toString()
//                ))
//            } ?: run {
//                viewModel.addTask(Task(
//                    id = System.currentTimeMillis(),
//                    title = binding.etTitle.text.toString(),
//                    description = binding.etDescription.text.toString(),
//                    priority = binding.etPriority.selectedItem.toString()
//                ))
//            }
//
//            Toast.makeText(requireContext(),"Data Saved successfully!", Toast.LENGTH_SHORT).show()
//            view?.findNavController()?.navigateUp()

    }
}