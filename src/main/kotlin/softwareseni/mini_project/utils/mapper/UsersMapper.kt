package softwareseni.mini_project.utils.mapper

import org.springframework.stereotype.Service
import softwareseni.mini_project.dto.UserDto
import softwareseni.mini_project.entity.User

@Service
class UsersMapper: Mapper<UserDto, User> {

    override fun fromEntity(entity: User): UserDto = UserDto(
        entity.id,
        entity.user,
        entity.pwd
    )

    override fun toEntity(domain: UserDto): User {
        val user = User()
        user.id = domain.id
        user.user = domain.user
        user.pwd = domain.pwd
        return user
    }
}