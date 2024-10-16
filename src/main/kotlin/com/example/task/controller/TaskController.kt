package com.example.task.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import com.example.task.model.category
import com.example.task.model.status
import org.springframework.web.bind.annotation.*
import com.example.task.model.TaskModel


@RestController
@RequestMapping("/api/tasks")
class TaskController {
    private val tasks = mutableListOf<TaskModel>()

    @GetMapping
    fun getAllTasks(): MutableList<TaskModel> {
        return tasks
    }

    @PostMapping("/add")
    fun addTask(@RequestBody task: TaskModel) {
        tasks.add(task)
    }

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): TaskModel? {
        return tasks.find { it.id == id }
    }

    @GetMapping("/name/{name}")
    fun getTaskByName(@PathVariable name: String): List<TaskModel> {
        return tasks.filter {
            it.title.equals(name, ignoreCase = true)
        }
    }

    @PutMapping("/{id}")
    fun updateTask(
        @PathVariable id:
        Long, @RequestBody updatedTask:
        TaskModel
    ) {
        val task: TaskModel? = tasks.find { it.id == id }
        task?.let {
            it.title = updatedTask.title
            it.description = updatedTask.description
            it.status = status.valueOf(updatedTask.status.toString())
            it.category = category.valueOf(updatedTask.category.toString())

        }
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Long) {
        tasks.removeIf { it.id == id }
    }
}
