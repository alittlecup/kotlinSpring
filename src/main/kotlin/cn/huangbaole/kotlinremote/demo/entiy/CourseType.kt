package cn.huangbaole.kotlinremote.demo.entiy

import java.util.Date
import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "t_course_type")
data class CourseType(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long=-1,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var duration: Int,
    @Column(nullable = false)
    var avator: String? = null,
    @Column(nullable = false)
    var desc: String? = null

)

@Entity
@Table(name = "t_course")
data class Course(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long=-1,
    @ManyToOne
    @JoinColumn(name = "course_type_id")
    var courseType: CourseType,
    @Column(nullable = false)
    var startTime: Date = Date(),
    @Column(nullable = false)
    @ManyToMany(mappedBy = "courses")
    var babys: List<Baby>? = null,
    @Column(nullable = false)
    var mark: String? = null
)