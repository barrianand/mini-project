package softwareseni.mini_project.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.i18n.CookieLocaleResolver
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import java.util.*


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = [
        "spring.datasource.url=jdbc:mysql://hq86e.h.filess.io:3307/miniproject_clawssort"
    ]
)
@AutoConfigureMockMvc
internal class MiniProjectControllerTest @Autowired constructor(
    var mockMvc: MockMvc,
    val objectMapper: ObjectMapper
){

    @Test
    fun createUserTest() {
    }

    @Test
    fun getAllUser() {
//        val key = Keys.secretKeyFor(SignatureAlgorithm.HS512)
//        val jwt = Jwts.builder()
//            .setIssuer(1.toString())
//            .setExpiration(Date(System.currentTimeMillis() + 60000)) // 1 hour
//            .signWith(key, SignatureAlgorithm.HS512).compact()
//        val cookie = Cookie("jwt", jwt)
//        cookie.setAttribute("id", "1")
//
//        val localeResolver = CookieLocaleResolver()
//        localeResolver.setCookieDomain("domain")
//        localeResolver.setCookieHttpOnly(true)
//        localeResolver.setCookieSameSite("foo")
//
//        mockMvc = standaloneSetup(SimpleController())
//            .addInterceptors(LocaleChangeInterceptor())
//            .addInterceptors(object : HandlerInterceptor {
//                override fun preHandle(
//                    request: HttpServletRequest,
//                    response: HttpServletResponse,
//                    handler: Any
//                ): Boolean {
//                    response.addCookie(cookie)
//                    return true
//                }
//            })
//            .setLocaleResolver(localeResolver)
//            .build()
//
//        mockMvc.get("/login")
//            .andDo { print() }
//            .andExpect {
//                cookie().exists("jwt")
//                status { isOk() }
//                content { contentType(MediaType.APPLICATION_JSON) }
//                jsonPath("$[0].id") { value("1") }
//            }
    }

    @Test
    fun getUserTest() {
    }

    @Test
    fun login() {
//        val userDto = UserDto(1,"user1","password1")
//
//        val performPost = mockMvc.post("/login") {
//            contentType = MediaType.APPLICATION_JSON
//            content = objectMapper.writeValueAsString(userDto)
//        }
//
//        performPost
//            .andDo { print() }
//            .andExpect {
//                status { isOk() }
//                content { contentType(MediaType.APPLICATION_JSON) }
//                jsonPath("$.id") { value(1) }
//            }
    }

    @Test
    fun logout() {
    }

    @Test
    fun createNewTask() {
    }

    @Test
    fun createUpdateTask() {
    }

    @Test
    fun getTaskDetails() {
    }

    @Test
    fun getTaskCreatedBy() {
    }

    @Test
    fun getTaskAssignedTo() {
    }

    @Test
    fun deleteTask() {
    }

    @Controller
    private class SimpleController {
        @RequestMapping("/")
        fun home(): String {
            return "home"
        }
    }

}