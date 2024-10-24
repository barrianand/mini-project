package softwareseni.mini_project.dto

import java.time.LocalDate

class CreateTaskDto (
    val title: String,
    val desc: String,
    val dueDate: LocalDate,
    val assignedTo: List<Int>
)