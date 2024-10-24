package softwareseni.mini_project.utils.mapper

import org.springframework.stereotype.Service
import softwareseni.mini_project.dto.TaskDto
import softwareseni.mini_project.dto.UserDto
import softwareseni.mini_project.entity.Task
import softwareseni.mini_project.entity.User

@Service
class TasksMapper : Mapper<TaskDto, Task> {

    override fun fromEntity(entity: Task): TaskDto = TaskDto(
        entity.id,
        entity.title,
        entity.desc,
        entity.status,
        entity.dueDate,
        entity.createdBy,
        entity.createdDate
    )

    override fun toEntity(domain: TaskDto): Task {
        val task = Task()
        task.id = domain.id
        task.title = domain.title
        task.desc = domain.desc
        task.status = domain.status
        task.dueDate = domain.dueDate
        task.createdBy = domain.createdBy
        task.createdDate = domain.createdDate
        return task
    }
}