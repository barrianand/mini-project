package softwareseni.mini_project.service

import softwareseni.mini_project.dto.UserDto
import softwareseni.mini_project.entity.User
import java.util.*

interface UserService {

    fun createUser(userDto: UserDto) : UserDto
    fun getUsers(id: Int) : UserDto
    fun getAllUsers(): List<User>
    fun findByUsername(user:String): User?
}