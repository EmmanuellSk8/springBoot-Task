package com.example.task.controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import com.example.task.model.category
import com.example.task.model.status
import org.springframework.scheduling.config.Task
import org.springframework.web.bind.annotation.*
import com.example.task.model.taskModel



@RestController
@RequestMapping("/api/tasks")
class TaskController {
    private val tasks = mutableListOf<taskModel>()

    @GetMapping
    fun getAllTasks(): MutableList<taskModel> {
        return tasks
    }

    @PostMapping("/add")
    fun addTask(@RequestBody task: taskModel){
        tasks.add(task)
    }

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): taskModel? {
        return tasks.find { it.id == id }
    }

    @GetMapping("/name/{name}")
    fun getTaskByName(@PathVariable name: String): List<taskModel> {
        return tasks.filter {
            it.title.equals(name, ignoreCase = true)
        }
    }

    @PutMapping("/{id}")
    fun updateTask(
        @PathVariable id:
        Long, @RequestBody updatedTask:
        taskModel
    ) {
        val task: taskModel? = tasks.find { it.id == id }
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
