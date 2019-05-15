package cn.huangbaole.kotlinremote.demo.entiy

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "t_user_baby_ship")
data class UserBabyShip(
    @Id @GeneratedValue(strategy = IDENTITY) var id: Long = -1,
    @Column(name = "user_id", nullable = false)
    var userId: Long,
    @Column(name = "baby_id", nullable = false)
    var babyId: Long
)