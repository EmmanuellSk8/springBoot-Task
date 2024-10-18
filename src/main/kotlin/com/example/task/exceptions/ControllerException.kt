package com.example.task.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

    @ControllerAdvice
    class ExceptionController {

        @ExceptionHandler(TaskNotFound::class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        fun handleTaskNotFound(ex: TaskNotFound): ResponseEntity<String> {
            return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
        }

    }

