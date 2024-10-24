package softwareseni.mini_project.service

import softwareseni.mini_project.dto.GetTaskResponseDto
import softwareseni.mini_project.dto.TaskDto
import softwareseni.mini_project.entity.Task
import java.util.Date

interface TaskService {
    fun createTask(taskDto: TaskDto, assigned_task : List<Int>) : Task
    fun getTask(id: Int) : Task?
    fun getTaskCreatedBy(id : Int): GetTaskResponseDto
    fun getTaskAssignedTo(id : Int): List<Task>?
    fun deleteById(id : Int) : Boolean
}