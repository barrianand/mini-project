package softwareseni.mini_project.service

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import softwareseni.mini_project.dto.GetTaskResponseDto
import softwareseni.mini_project.dto.TaskDto
import softwareseni.mini_project.entity.Task
import softwareseni.mini_project.entity.TaskManagement
import softwareseni.mini_project.entity.User
import softwareseni.mini_project.repository.TaskManagementRepository
import softwareseni.mini_project.repository.TaskRepository
import softwareseni.mini_project.repository.UserRepository
import softwareseni.mini_project.utils.mapper.TasksMapper
import java.util.*

@Service
class TaskServiceImpl(
    private val taskRepository : TaskRepository,
    private val taskManagementRepository: TaskManagementRepository,
    private val tasksMapper : TasksMapper
) : TaskService {

    override fun createTask(taskDto: TaskDto, assigned_task : List<Int>): Task {
        val task = tasksMapper.toEntity(taskDto)
        taskRepository.save(task)

        assigned_task.map {
            val taskManagement = TaskManagement()
            taskManagement.userId = it
            taskManagement.assignedTaskId = task.id
            taskManagementRepository.save(taskManagement)

        }
        return task
    }

    override fun getTask(id: Int): Task? {
        return taskRepository.findById(id).get()
    }

    override fun getTaskCreatedBy(id: Int): GetTaskResponseDto {
        val task = taskRepository.findByCreatedBy(id)
        return GetTaskResponseDto(task)

    }

    override fun getTaskAssignedTo(id: Int): List<Task>? {
        val getAllTask = taskManagementRepository.findByUserId(id)
        val listTask : List<Task> = taskRepository.findAllById(getAllTask.map {
            it.assignedTaskId
        })
        return listTask
    }

    override fun deleteById(id: Int): Boolean {
        taskRepository.deleteById(id)
        return true
    }


}