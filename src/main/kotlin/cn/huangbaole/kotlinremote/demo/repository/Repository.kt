package cn.huangbaole.kotlinremote.demo.repository

import cn.huangbaole.kotlinremote.demo.entiy.Baby
import cn.huangbaole.kotlinremote.demo.entiy.User
import cn.huangbaole.kotlinremote.demo.entiy.UserBabyShip
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>
interface BabyRepository : JpaRepository<Baby, Long>
interface UserBabyShipRepository : JpaRepository<UserBabyShip, Long> {
  fun findByBabyIdAndUserId(babyId: Long, userId: Long): UserBabyShip?
}