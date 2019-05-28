package cn.huangbaole.kotlinremote.demo.repository

import cn.huangbaole.kotlinremote.demo.entiy.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {
  fun findByUsernameContainsIgnoringCase(userName: String): List<User>?

  @Query("SELECT new User(u.id,u.username,u.avatar,u.phone,u.state) FROM User u")
  fun findSimpleUserInfo(): List<User>?
}