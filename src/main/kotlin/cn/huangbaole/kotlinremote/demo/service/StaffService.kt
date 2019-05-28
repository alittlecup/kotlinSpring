package cn.huangbaole.kotlinremote.demo.service

import cn.huangbaole.kotlinremote.demo.entiy.Staff

interface StaffService : BaseService<Staff> {
  fun findTrainer(): List<Staff>?
  fun findAllStaffByName(name:String): List<Staff>?
}