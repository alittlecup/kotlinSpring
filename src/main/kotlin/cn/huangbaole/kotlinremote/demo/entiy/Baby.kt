package cn.huangbaole.kotlinremote.demo.entiy

import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
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
    var mark: String? = null

) {
  constructor(id: Long, name: String, nickname: String, avatar: String, gender: Boolean,
      age: Int) : this() {
    this.id = id
    this.name = name
    this.nickname = nickname
    this.avatar = avatar
    this.gender = gender
    this.age = age
  }

  override fun toString(): String {
    return "Baby(id=$id, name='$name', nickname='$nickname', avatar='$avatar', gender=$gender, age=$age, birthday=$birthday, createTime=$createTime, mark=$mark)"
  }

  fun check(): String {
    return when {
      name.isNullOrEmpty() && nickname.isNullOrEmpty() -> "姓名或者乳名不能为空"
      age <= 0 -> "年龄不能为空"
      else -> ""
    }
  }
}