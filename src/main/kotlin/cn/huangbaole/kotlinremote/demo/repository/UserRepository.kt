package cn.huangbaole.kotlinremote.demo.repository

import cn.huangbaole.kotlinremote.demo.entiy.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
  fun findByUsernameAllIgnoringCase(userName: String): List<User>?

}