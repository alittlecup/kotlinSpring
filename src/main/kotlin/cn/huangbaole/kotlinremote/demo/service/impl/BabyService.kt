package cn.huangbaole.kotlinremote.demo.service.impl

import cn.huangbaole.kotlinremote.demo.entiy.Baby
import cn.huangbaole.kotlinremote.demo.entiy.User
import cn.huangbaole.kotlinremote.demo.repository.BabyRepository
import cn.huangbaole.kotlinremote.demo.service.IBabyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BabyService : IBabyService {
  @Autowired
  lateinit var babyRepository: BabyRepository

  override fun saveBaby(user: Baby): Baby = babyRepository.save(user)


  override fun findBaby(id: Long): Baby = babyRepository.getOne(id)

  override fun deleteBaby(id: Long): Boolean {
    var existsById = babyRepository.existsById(id)
    return if (existsById) {
      babyRepository.deleteById(id)
      true
    } else {
      false
    }
  }

  override fun loadBabies(): List<Baby> =babyRepository.findAll()
}