package softwareseni.mini_project.repository;

import org.springframework.data.jpa.repository.JpaRepository
import softwareseni.mini_project.entity.User

interface UserRepository : JpaRepository<User, Int> {
    fun findByUser(user:String) : User?
}