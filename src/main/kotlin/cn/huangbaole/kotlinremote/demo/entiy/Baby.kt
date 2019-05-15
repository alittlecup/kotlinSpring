package cn.huangbaole.kotlinremote.demo.entiy

import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "t_baby")
data class Baby(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(nullable = true)
    var name: String,
    @Column(nullable = true)
    var nickname: String,
    @Column(nullable = false)
    var gender: Boolean,
    @Column(nullable = false)
    var age: Int,
    @Column(nullable = false)
    var createTime: Date = Date()

)