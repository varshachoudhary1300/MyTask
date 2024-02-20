package com.my.rebuildtask.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.my.rebuildtask.databinding.DetailItemBinding
import com.my.rebuildtask.model.Task

class TaskAdapter(private val click :(task: Task) -> Unit) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    private val taskList = mutableListOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val taskInfo = taskList[position]

            holder.itemView.setOnClickListener {
                click.invoke(taskInfo)
            }

        holder.bind(taskInfo)
    }


    class ViewHolder(private val binding : DetailItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(taskInfo: Task){
            if (taskInfo.description != null) {
                // Make sure the taskInfo is not empty before setting it
                binding.tvTask.text = taskInfo.title
                binding.tvDescription.text = taskInfo.description
                binding.tvPriority.text = taskInfo.priority
            }
        }
    }

    fun submitList(tasks: List<Task>) {
        taskList.clear()
        taskList.addAll(tasks)
        notifyDataSetChanged()
    }


}