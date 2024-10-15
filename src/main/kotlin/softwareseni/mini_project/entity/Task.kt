package softwareseni.mini_project.entity

import jakarta.persistence.*
import softwareseni.mini_project.entity.User
import java.time.LocalDate

@Entity
@Table(name = "tasks")
open class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @Column(name = "title", nullable = false, length = 250)
    open var title: String? = null

    @Column(name = "`desc`", length = 250)
    open var desc: String? = null

    @Column(name = "status", length = 250)
    open var status: String? = null

    @Column(name = "due_date")
    open var dueDate: LocalDate? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    open var createdBy: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to")
    open var assignedTo: User? = null
}