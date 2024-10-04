package com.example.task.controller

import org.springframework.scheduling.config.Task
import org.springframework.web.bind.annotation.*
import com.example.task.model.taskModel

@RestController
@RequestMapping("/api/tasks")
class TaskController {
    private val tasks = mutableListOf<Task>()
    @GetMapping
    fun getAllTasks(): List<Task> {
        return tasks
    }

    @PostMapping
    fun addTask(@RequestBody task: Task) {
        tasks.add(task)
    }

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): Task? {
        return tasks.find { it.id == id }
    }
    @GetMapping("/name/{name}")
    fun getTaskByName(@PathVariable name: String): List<Task> {
        return tasks.filter { it.title.equals(name, ignoreCase = true)
        }
    }
    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: String, @RequestBody updatedTask:
    Task) {
        val task = tasks.find { it.id == id }
        task?.let {
            it.title = updatedTask.title
            it.description =updatedTask.description
            it.status = updatedTask.status

        }
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: String) {
        tasks.removeIf { it.id == id }
    }
}
