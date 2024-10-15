package softwareseni.mini_project

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import softwareseni.mini_project.entity.User
import softwareseni.mini_project.repository.UserRepository

@RestController
@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class MiniProjectApplication {
}

fun main(args: Array<String>) {
	runApplication<MiniProjectApplication>(*args)
}
