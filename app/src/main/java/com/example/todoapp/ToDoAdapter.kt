package com.example.todoapp

import android.graphics.Paint
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*


class ToDoAdapter(
        private val todos: MutableList<ToDo>
    ) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>(){
    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        )
    }

    fun addToDo(todo: ToDo) {
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }

    fun deleteDoneToDos() {
        todos.removeAll { todo -> todo.isChecked }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvToDoTittle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvToDoTittle.paintFlags = tvToDoTittle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvToDoTittle.paintFlags = tvToDoTittle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentToDo = todos[position]
        holder.itemView.apply {
            tvToDoTittle.text = currentToDo.title
            cbDone.isChecked = currentToDo.isChecked
            toggleStrikeThrough(tvToDoTittle, currentToDo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvToDoTittle, isChecked)
                currentToDo.isChecked = !currentToDo.isChecked
            }
        }
    }



    override fun getItemCount(): Int {
        return todos.size
    }
}