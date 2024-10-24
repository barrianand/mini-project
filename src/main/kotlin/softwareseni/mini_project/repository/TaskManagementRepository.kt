package softwareseni.mini_project.repository;

import org.springframework.data.jpa.repository.JpaRepository
import softwareseni.mini_project.entity.TaskManagement

interface TaskManagementRepository : JpaRepository<TaskManagement, Int> {
    fun findByAssignedTaskId(id : Int) : List<TaskManagement>
    fun findByUserId(id : Int) : List<TaskManagement>
}