package cn.huangbaole.kotlinremote.demo.service

import cn.huangbaole.kotlinremote.demo.entiy.Baby
import cn.huangbaole.kotlinremote.demo.entiy.User


interface IBabyService {
  fun saveBaby(user: Baby):Baby
  fun findBaby(id:Long):Baby
  fun deleteBaby(id: Long):Boolean
  fun loadBabies():List<Baby>
}