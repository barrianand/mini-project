package softwareseni.mini_project.dto

import java.time.LocalDate

data class TaskDto(
    val id: Int,
    val title: String,
    val desc: String,
    val status: String,
    val dueDate: LocalDate,
    val createdBy: Int,
    val assignedTo: Int
)
