package softwareseni.mini_project.controller

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import softwareseni.mini_project.dto.*
import softwareseni.mini_project.entity.Task
import softwareseni.mini_project.entity.User
import softwareseni.mini_project.service.TaskService
import softwareseni.mini_project.service.UserService
import softwareseni.mini_project.utils.mapper.TasksMapper
import softwareseni.mini_project.utils.mapper.UsersMapper
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*


@RestController
class MiniProjectController (
    private val userService: UserService,
    private val taskService : TaskService,
    private val tasksMapper: TasksMapper,
    private val usersMapper: UsersMapper
){
    @PostMapping("/createUser")
    fun createUser(@RequestBody createUserDto : CreateUserDto) : ResponseEntity<Any> {
        val user = User()
        user.user = createUserDto.user
        user.pwd = createUserDto.password

        val findUser = this.userService.findByUsername(createUserDto.user)
        if (findUser != null) {
            return ResponseEntity.badRequest().body(Message("user already exist!"))
        }

        val userDto : UserDto = userService.createUser(usersMapper.fromEntity(user))
        return ResponseEntity.ok(usersMapper.toEntity(userDto))
    }

    @GetMapping("/getUsers")
    fun getAllUser(@CookieValue("jwt") jwt: String?) : ResponseEntity<List<Any>> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body(listOf(Message("unauthenticated")))
            }
            return ResponseEntity.ok(userService.getAllUsers())
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity.status(401).body(listOf(Message("Error getting data from database")))
        }

    }

    @GetMapping("/getUserById/{id}")
    fun getUser(@PathVariable id: Int, @CookieValue("jwt") jwt: String?) : ResponseEntity<Any> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body(Message("unauthenticated"))
            }
            return ResponseEntity.ok(userService.getUsers(id))
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(Message("Error getting with ID : $id"))
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody body: UserDto, response: HttpServletResponse): ResponseEntity<Any> {
        val user = this.userService.findByUsername(body.user)
            ?: return ResponseEntity.badRequest().body(Message("user not found!"))

        if (!user.comparePassword(body.pwd)) {
            return ResponseEntity.badRequest().body(Message("invalid password!"))
        }

        val issuer = user.id.toString()
        val key = Keys.secretKeyFor(SignatureAlgorithm.HS512)

        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60000)) // 1 hour
            .signWith(key, SignatureAlgorithm.HS512).compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true
        val cookieId = Cookie("id", issuer)
        cookie.isHttpOnly = true

        response.addCookie(cookie)
        response.addCookie(cookieId)

        return ResponseEntity.ok(Message("success"))
    }

    @PostMapping("/logout")
    fun logout(response: HttpServletResponse): ResponseEntity<Any> {
        val cookie = Cookie("jwt", "")
        val cookieID = Cookie("id", "")
        cookie.maxAge = 0
        cookieID.maxAge = 0

        response.addCookie(cookieID)
        response.addCookie(cookie)

        return ResponseEntity.ok(Message("success"))
    }

    @PostMapping("/createTask")
    fun createNewTask(@RequestBody createTaskDto: CreateTaskDto, @CookieValue("jwt") jwt: String?, @CookieValue("id") userId: Int?) : Any {
        try {
            if (jwt == null || userId == null) {
                return ResponseEntity.status(401).body(Message("unauthenticated"))
            }
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")
            val createdDate: String = LocalDate.now()
                .atTime(LocalTime.now()).atOffset(OffsetDateTime.now().offset).format(formatter)

            val dueDate : String = createTaskDto.dueDate
                .atStartOfDay().atOffset(OffsetDateTime.now().offset).format(formatter)
            println("created date : $createdDate")
            println("due date : $dueDate")

            val task = Task()
            task.title = createTaskDto.title
            task.desc = createTaskDto.desc
            task.status = 1
            task.dueDate = Timestamp.valueOf(dueDate)
            task.createdBy = userId
            task.createdDate = Timestamp.valueOf(createdDate)

            val taskDto : TaskDto = tasksMapper.fromEntity(task)
            return ResponseEntity.ok(taskService.createTask(taskDto, createTaskDto.assignedTo))
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity.status(401).body(Message("Error creating task exception : $e"))
        }
    }

    @PostMapping("/updateTask")
    fun createUpdateTask(@RequestBody updateTaskRequest : UpdateTaskRequest, @CookieValue("jwt") jwt: String?, @CookieValue("id") userId: Int) : Any {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body(Message("unauthenticated"))
            }
            val taskDto = TaskDto(
                id = updateTaskRequest.id,
                title = updateTaskRequest.title,
                desc = updateTaskRequest.desc,
                status = updateTaskRequest.status,
                dueDate = updateTaskRequest.dueDate,
                createdBy = updateTaskRequest.createdBy,
                createdDate = updateTaskRequest.createdDate
            )
            return ResponseEntity.ok(taskService.createTask(taskDto,updateTaskRequest.assignedTo))
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity.status(401).body(Message("Error creating task exception : $e"))
        }
    }

    @GetMapping("/viewTask/{id}")
    fun getTaskDetails(@CookieValue("jwt") jwt: String?, @PathVariable id: Int) : ResponseEntity<Any> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body(Message("unauthenticated"))
            }
            return ResponseEntity.ok(taskService.getTask(id))
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity.status(401).body(Message("Error getting data from database"))
        }

    }

    @GetMapping("/getTaskCreatedBy/{id}")
    fun getTaskCreatedBy(@CookieValue("jwt") jwt: String?, @PathVariable id: Int) : ResponseEntity<Any> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body(listOf(Message("unauthenticated")))
            }
            return ResponseEntity.ok(taskService.getTaskCreatedBy(id))
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity.status(401).body(listOf(Message("Error getting data from database")))
        }
    }

    @GetMapping("/getTaskAssignedTo/{id}")
    fun getTaskAssignedTo(@CookieValue("jwt") jwt: String?, @PathVariable id: Int) : ResponseEntity<List<Any>> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body(listOf(Message("unauthenticated")))
            }
            return ResponseEntity.ok(taskService.getTaskAssignedTo(id))
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity.status(401).body(listOf(Message("Error getting data from database")))
        }
    }

    @PostMapping("/deleteTask/{id}")
    fun deleteTask(@PathVariable id: Int): ResponseEntity<Any> {
        val task = this.taskService.getTask(id)
            ?: return ResponseEntity.badRequest().body(Message("task not found or deleted!"))
        taskService.deleteById(task.id)
        return ResponseEntity.ok(Message("Delete Task Successfully!"))
    }
}