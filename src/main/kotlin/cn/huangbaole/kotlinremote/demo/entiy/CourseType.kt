package cn.huangbaole.kotlinremote.demo.entiy

import java.util.Date
import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "t_course_type")
data class CourseType(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long = -1,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var duration: Int,
    @Column(nullable = false)
    var avatar: String? = null,
    @Column(nullable = false)
    var fdesc: String? = null

)

@Entity
@Table(name = "t_course")
data class Course(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long = -1,
    @OneToOne
    @JoinColumn(name = "course_type_id")
    var courseType: CourseType,
    @Column(nullable = false)
    var startTime: Date = Date(),
    @Column(nullable = false)
    @ManyToMany
    @JoinTable(name = "course_baby_inner",
        inverseJoinColumns = [JoinColumn(name = "baby_id", referencedColumnName = "id")],
        joinColumns = [JoinColumn(name = "course_id", referencedColumnName = "id")])
    var babies: List<Baby>?,

    @Column(nullable = false)
    var mark: String? = null
)