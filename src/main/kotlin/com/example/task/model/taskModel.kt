package com.example.task.model

data class taskModel(var id: Long,
                     var title: String,
                     var description: String,
                     var status : status,
                     var category: category)

enum class status{
    complete,
    pending
}

enum class category{
    easy,
    medium,
    hard
}