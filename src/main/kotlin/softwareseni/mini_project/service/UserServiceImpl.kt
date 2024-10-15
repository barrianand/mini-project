package softwareseni.mini_project.service

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import softwareseni.mini_project.dto.UserDto
import softwareseni.mini_project.entity.User
import softwareseni.mini_project.repository.UserRepository
import softwareseni.mini_project.utils.mapper.UsersMapper

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val usersMapper: UsersMapper
) : UserService {
    override fun createUser(userDto: UserDto) : UserDto {
        val user = usersMapper.toEntity(userDto)
        userRepository.save(user)
        return usersMapper.fromEntity(user)
    }

    override fun getUsers(id: Int): UserDto {
        return usersMapper.fromEntity(userRepository.findById(id).get())
    }

    override fun getAllUsers(): List<User> {
        val user = userRepository.findAll()
        val passwordEncoder = BCryptPasswordEncoder()

        user.map {
            it.pwd=passwordEncoder.encode(it.pwd)
        }
        return user
    }

    override fun findByUsername(user: String): User? {
        val userMatched = userRepository.findByUser(user)
        val passwordEncoder = BCryptPasswordEncoder()
        if (userMatched != null) {
            userMatched.pwd = passwordEncoder.encode(userMatched.pwd)
            println("user pass : " +userMatched.pwd)
        }
        return userMatched
    }


}