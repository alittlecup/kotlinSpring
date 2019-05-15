package cn.huangbaole.kotlinremote.demo.entiy

import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "t_user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1,
    @Column(nullable = false)
    var username: String? = null,
    @Column(nullable = false)
    var password: String? = "123456",
    @Column(nullable = false)
    var email: String? = null,
    @Column(nullable = false)
    var createTime: Date = Date(),
    @Column(nullable = false)
    var state: Int = 0
)