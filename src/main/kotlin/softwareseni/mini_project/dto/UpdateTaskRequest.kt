package softwareseni.mini_project.dto

import java.sql.Timestamp

class UpdateTaskRequest (
    var id: Int,
    val title: String,
    val desc: String,
    val status: Int,
    val dueDate: Timestamp,
    var createdBy: Int,
    var assignedTo : List<Int>,
    val createdDate : Timestamp
)