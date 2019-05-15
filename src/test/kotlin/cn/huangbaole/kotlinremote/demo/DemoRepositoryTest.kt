package cn.huangbaole.kotlinremote.demo

import cn.huangbaole.kotlinremote.demo.entiy.User
import cn.huangbaole.kotlinremote.demo.entiy.UserBabyShip
import cn.huangbaole.kotlinremote.demo.repository.BabyRepository
import cn.huangbaole.kotlinremote.demo.repository.UserBabyShipRepository
import cn.huangbaole.kotlinremote.demo.repository.UserRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.Date

@RunWith(SpringRunner::class)
@SpringBootTest
class UserRepositoryTest {
  @Autowired
  private lateinit var userRepository: UserRepository
  @Autowired
  private lateinit var babyRepository: BabyRepository
  @Autowired
  private lateinit var userBabyShipRepository: UserBabyShipRepository

  @Test
  fun testUser() {
    userRepository.deleteAll()
    userRepository.save(
        User(username = "Tom", email = "123@164.com", createTime = Date(), state = 0,
            password = "123456"))
    var findAll = userRepository.findAll()
    for (user in findAll) {
      user.username = "tom222"
    }
    userRepository.saveAll(findAll)
    print(userRepository.findAll())

  }

  @Test
  fun testUserBabyShip() {
    userBabyShipRepository.deleteAll()
    userBabyShipRepository.save(UserBabyShip(babyId = 1, userId = 1))
    userBabyShipRepository.save(UserBabyShip(babyId = 2, userId = 1))
    userBabyShipRepository.save(UserBabyShip(babyId = 3, userId = 2))
    print(userBabyShipRepository.findAll())
    print(userBabyShipRepository.findByBabyIdAndUserId(2, 1))
  }
}