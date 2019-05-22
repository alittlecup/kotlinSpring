package cn.huangbaole.kotlinremote.demo.repository

import cn.huangbaole.kotlinremote.demo.entiy.Baby
import cn.huangbaole.kotlinremote.demo.entiy.User
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class UserRepositoryTests {
  @Autowired
  private lateinit var userRepository: UserRepository
  @Autowired
  private lateinit var babyRepository: BabyRepository

  @Test
  fun testUser() {
    var user = User(username = "Tom", gender = false, avatar = "", phone = "13300980989", state = 0,
        address = "", mark = "鱼子介绍")
    userRepository.save(user)
    var tom = userRepository.findByUsernameAllIgnoringCase("tom")
    if (tom?.isNotEmpty()!!) {
      var baby = Baby(name = "TomS", avatar = "", age = 5, gender = false, nickname = "tt",
          parent = tom[0])
      var babyt = Baby(name = "TomT", avatar = "", age = 5, gender = false, nickname = "ss",
          parent = tom[0])
      babyRepository.saveAll(arrayListOf(baby,babyt))
    }
    var tomBabys = babyRepository.findByParent_UsernameAllIgnoringCase(
        "tom")
    print(tomBabys)
  }
}