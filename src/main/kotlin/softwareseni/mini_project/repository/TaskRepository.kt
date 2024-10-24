package softwareseni.mini_project.repository

import org.springframework.data.jpa.repository.JpaRepository
import softwareseni.mini_project.entity.Task

interface TaskRepository : JpaRepository<Task, Int> {
    fun findByCreatedBy(id : Int) : List<Task>
}