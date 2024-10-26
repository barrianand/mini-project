package softwareseni.mini_project.dto

import java.sql.Date
import java.sql.Timestamp

data class TaskDto(
    var id: Int,
    val title: String,
    val desc: String,
    val status: Int,
    val dueDate: Timestamp,
    var createdBy: Int,
    val createdDate : Timestamp
)
