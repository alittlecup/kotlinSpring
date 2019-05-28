package cn.huangbaole.kotlinremote.demo.repository

import cn.huangbaole.kotlinremote.demo.entiy.Staff
import cn.huangbaole.kotlinremote.demo.entiy.StaffPosition
import org.springframework.data.jpa.repository.JpaRepository

interface StaffRepostory : JpaRepository<Staff, Long> {
  fun findByPositionIs(staffPosition: StaffPosition): List<Staff>?
  fun findByNameContainsIgnoringCase(name: String): List<Staff>?
}