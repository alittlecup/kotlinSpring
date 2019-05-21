package cn.huangbaole.kotlinremote.demo.repository

import cn.huangbaole.kotlinremote.demo.entiy.Baby
import cn.huangbaole.kotlinremote.demo.entiy.User
import org.springframework.data.jpa.repository.JpaRepository

interface BabyRepository : JpaRepository<Baby, Long> {
  fun findByParent_UsernameAllIgnoringCase(parentName: String): List<Baby>?
}