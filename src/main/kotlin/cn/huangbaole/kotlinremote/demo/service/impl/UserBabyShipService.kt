package cn.huangbaole.kotlinremote.demo.service.impl

import cn.huangbaole.kotlinremote.demo.entiy.UserBabyShip
import cn.huangbaole.kotlinremote.demo.repository.UserBabyShipRepository
import cn.huangbaole.kotlinremote.demo.service.IUserBabyShipService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserBabyShipService : IUserBabyShipService {
  @Autowired
  lateinit var userBabyShipRepository: UserBabyShipRepository

  override fun saveBabyAsUserChild(babyId: Long, userId: Long) {
    var userBabyShip = UserBabyShip(userId = userId, babyId = babyId)
    userBabyShipRepository.save(userBabyShip)
  }

  override fun removeUserChild(babyId: Long, userId: Long) {
    var userBabyShip = userBabyShipRepository.findByBabyIdAndUserId(userId = userId,
        babyId = babyId)
    userBabyShipRepository.deleteById(userBabyShip?.id!!)
  }
}
