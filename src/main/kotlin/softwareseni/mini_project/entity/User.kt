package softwareseni.mini_project.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Entity
@Table(name = "users")
class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int = 0

    @Column(name = "user", unique = true)
    var user = ""

    @Column(name = "pwd")
    @JsonIgnore
    var pwd = ""

    fun comparePassword(password: String): Boolean {
        println("hashed pass : " + this.pwd)
        return BCryptPasswordEncoder().matches(password, this.pwd)
    }
}