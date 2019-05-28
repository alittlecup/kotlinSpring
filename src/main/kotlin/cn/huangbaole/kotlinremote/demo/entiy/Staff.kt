package cn.huangbaole.kotlinremote.demo.entiy

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "t_staff")
data class Staff(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long = -1,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false, length = 11)
    var phone: Int,
    @Column(nullable = false)
    var password: String,
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    var position: StaffPosition?
) {
  fun checkSelf(): String {
    return when {
      name.isNullOrEmpty() -> "用户名不能为空"
      "$phone".length <= 11 -> "手机号错误"
      password.length < 6 -> "密码应大于 6 位"
      position == null -> "职位不能为空"
      else -> ""
    }
  }
}

enum class StaffPosition {
  SUPER_USER, TRAINER, FRONT_DESK, SERVICE
}