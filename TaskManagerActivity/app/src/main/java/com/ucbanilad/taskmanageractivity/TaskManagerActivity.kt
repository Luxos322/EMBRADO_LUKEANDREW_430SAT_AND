package com.ucbanilad.taskmanageractivity

import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_manager);

        // Initialize Views
        // Initialize Views
        editTextTask = findViewById<View>(R.id.editTextTask)
        spinnerCategory = findViewById<View>(R.id.spinnerCategory)
        buttonAdd = findViewById<View>(R.id.buttonAdd)
        listViewTasks = findViewById<View>(R.id.listViewTasks)

        // Task List

        // Task List
        taskList = ArrayList<Any>()

        // Set up Spinner

        // Set up Spinner
        val categories = arrayOf("Work", "Personal", "Urgent")
        val categoryAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        spinnerCategory.setAdapter(categoryAdapter)

        spinnerCategory.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                selectedCategory = categories[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedCategory = categories[0]
            }
        })

// Set up ListView

// Set up ListView
        taskAdapter = ArrayAdapter<Any?>(this, R.layout.simple_list_item_1, taskList)
        listViewTasks.setAdapter(taskAdapter)

        // Button Click Listener

        // Button Click Listener
        buttonAdd.setOnClickListener(View.OnClickListener {
            val taskName: String = editTextTask.getText().toString().trim()
            if (taskName.isEmpty()) {
                Toast.makeText(this@TaskManagerActivity, "Please enter a task", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val taskWithCategory = "$taskName ($selectedCategory)"
                taskList.add(taskWithCategory)
                taskAdapter.notifyDataSetChanged()
                editTextTask.setText("")
            }
        })

    }
}
