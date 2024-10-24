package softwareseni.mini_project.entity

import jakarta.persistence.*

@Entity
@Table(name = "task_management")
class TaskManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int = 0

    @Column(name = "user_id")
    var userId: Int = 0

    @Column(name = "assigned_task_id")
    var assignedTaskId: Int = 0
}