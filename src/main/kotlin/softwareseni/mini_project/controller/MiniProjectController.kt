package softwareseni.mini_project.controller

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import softwareseni.mini_project.dto.Message
import softwareseni.mini_project.dto.UserDto
import softwareseni.mini_project.entity.User
import softwareseni.mini_project.service.UserService
import java.util.*

@RestController
class MiniProjectController (
    private val userService: UserService
){
    @PostMapping
    fun createUser(@RequestBody userDto: UserDto) : UserDto {
        return userService.createUser(userDto)
    }

    @GetMapping("/getUsers")
    fun getAllUser() : ResponseEntity<List<User>> =
        ResponseEntity.ok(userService.getAllUsers())

    @GetMapping("/getUserById/{id}")
    fun getUser(@PathVariable id: Int, @CookieValue("jwt") jwt: String?) : ResponseEntity<Any> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body(Message("unauthenticated"))
            }
            return ResponseEntity.ok(userService.getUsers(id))
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(Message("unauthenticated"))
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
        //Keys.hmacShaKeyFor(Encoders.BASE64.encode("secret".toByteArray()).toByteArray())

        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60000)) // 1 hour
            .signWith(key, SignatureAlgorithm.HS512).compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("success"))
    }

    @PostMapping("/logout")
    fun logout(response: HttpServletResponse): ResponseEntity<Any> {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("success"))
    }

}