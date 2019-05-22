package cn.huangbaole.kotlinremote.demo.entiy

import org.hibernate.annotations.NaturalId
import java.util.Date
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.REFRESH
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "t_baby")
data class Baby(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1,
    @Column(nullable = true)
    var name: String = "",
    @Column(nullable = true)
    var nickname: String = "",
    @Column(nullable = true)
    var avatar: String = "",
    @Column(nullable = false)
    var gender: Boolean = true,
    @Column(nullable = false)
    var age: Int = -1,
    @Column(nullable = true)
    var birthday: Date = Date(),
    @Column(nullable = false)
    var createTime: Date = Date(),
    @Column(nullable = true)
    var mark: String? = null,

    @ManyToOne(optional = false)
    @JoinColumn(name = "parent_id")
    var parent: User,

    @ManyToMany
    @JoinTable(name = "baby_course_inner",
        joinColumns = [JoinColumn(name = "baby_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "course_id", referencedColumnName = "id")])
    var courses: List<Course>? = null

) {
  override fun toString(): String {
    return "Baby(id=$id, name='$name', nickname='$nickname', avatar='$avatar', gender=$gender, age=$age, birthday=$birthday, createTime=$createTime, mark=$mark, courses=$courses)"
  }
}