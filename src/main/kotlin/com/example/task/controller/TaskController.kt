package com.example.task.controller


import com.example.task.model.TaskModel
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import com.example.task.model.category
import com.example.task.model.status
import org.springframework.web.bind.annotation.*
import com.example.task.exceptions.TaskNotFound
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


@RestController
@RequestMapping("/api/tasks")
class TaskController {
    private val tasks = mutableListOf<TaskModel>()

    @GetMapping
    fun getAllTasks(): MutableList<TaskModel> {
        return tasks
    }

    @PostMapping("/add")
    fun addTask(@RequestBody task: TaskModel): ResponseEntity<TaskModel> {
        tasks.add(task)
        return ResponseEntity(task, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): TaskModel? {
        return tasks.find {
            it.id == id
        } ?: throw TaskNotFound("La tarea con el id $id no ha sido encontrada")
    }

    @GetMapping("/name/{name}")
    fun getTaskByName(@PathVariable name: String): List<TaskModel> {
        if (name.isNullOrEmpty()) {
            throw TaskNotFound("La tarea con el nombre $name no ha sido encontrada")
        }
        return tasks.filter {
            it.title.equals(name, ignoreCase = true)
        }
    }


    @GetMapping("/category/{category}")
    fun getTaskByCategory(@RequestParam category: String): List<TaskModel> {
        val listCategory =
            tasks.filter {
                it.category.name.equals(category, true)
            }
        if (category.isNullOrEmpty()) {
            throw TaskNotFound("La tarea en la categoría $category no fue encontrada")

        }
        return listCategory
    }


    @PutMapping("/{id}")
    fun updateTask(
        @PathVariable id:
        Long, @RequestBody updatedTask:
        TaskModel
    ) {
        val task: TaskModel? =
            tasks.find { it.id == id } ?: throw TaskNotFound("No se pudo encontrar la tarea con el id $id")
        task?.let {
            it.title = updatedTask.title
            it.description = updatedTask.description
            it.status = status.valueOf(updatedTask.status.toString())
            it.category = category.valueOf(updatedTask.category.toString())

        }
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Long) {
        if (!tasks.removeIf { it.id == id }) {
            throw TaskNotFound("No se pudo eliminar la tarea con el id $id")
        }
    }
}