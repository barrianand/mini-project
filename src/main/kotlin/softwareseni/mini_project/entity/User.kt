package softwareseni.mini_project.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Entity
@Table(name = "users")
class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column(unique = true)
    var user = ""

    @Column(name = "pwd")
    var pwd = ""
//        @JsonIgnore
//        get() = field
//        set(value) {
//            val passwordEncoder = BCryptPasswordEncoder()
//            field = passwordEncoder.encode(value)
//        }

    fun comparePassword(password: String): Boolean {
        println("hashed pass : " + this.pwd)
        val passwordEncoder = BCryptPasswordEncoder()
        return BCryptPasswordEncoder().matches(password, this.pwd)
    }
}