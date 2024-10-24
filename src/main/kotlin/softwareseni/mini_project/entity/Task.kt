package softwareseni.mini_project.entity

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.sql.Date
import java.sql.Timestamp
import java.time.*
import java.time.format.DateTimeFormatter

@Entity
@Table(name = "tasks")
class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int = 0

    @Column(name = "title", nullable = false, length = 250)
    var title: String = ""

    @Column(name = "`desc`", length = 250)
    var desc: String = ""

    @Column(name = "status", length = 250)
    var status: Int = 0

    @Column(name = "due_date")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    var dueDate: Timestamp = Timestamp.valueOf(LocalDate.now()
        .atTime(LocalTime.now())
        .atOffset(OffsetDateTime.now().offset)
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))

    @Column(name = "created_by")
    var createdBy: Int = 0

    @Column(name = "created_date")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    var createdDate: Timestamp = Timestamp.valueOf(LocalDate.now()
        .atTime(LocalTime.now())
        .atOffset(OffsetDateTime.now().offset)
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
}