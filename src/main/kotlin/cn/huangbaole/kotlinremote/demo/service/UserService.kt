package cn.huangbaole.kotlinremote.demo.service

import cn.huangbaole.kotlinremote.demo.entiy.Baby
import cn.huangbaole.kotlinremote.demo.entiy.User


interface UserService : BaseService<User> {
  fun findUserByName(name: String): List<User>?
}

interface BabyService : BaseService<Baby> {
  fun findBabyByName(name: String): List<Baby>?
  fun addBaby(userId: Long, baby: Baby): Baby
  fun delBaby(userId: Long, babyId: Long):Boolean
  fun findByParentId(parentId: Long): List<Baby>?
}