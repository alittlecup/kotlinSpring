package cn.huangbaole.kotlinremote.demo.repository

import cn.huangbaole.kotlinremote.demo.entiy.Baby
import cn.huangbaole.kotlinremote.demo.entiy.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BabyRepository : JpaRepository<Baby, Long> {
  fun findByNameContainsIgnoringCase(name: String): List<Baby>?

  @Query("select new Baby (b.id,b.name,b.nickname,b.avatar,b.gender,b.age) from Baby b")
  fun findSimpleBabies(): List<Baby>?
}
