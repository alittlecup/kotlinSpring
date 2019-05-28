package cn.huangbaole.kotlinremote.demo.repository

import cn.huangbaole.kotlinremote.demo.entiy.Baby
import cn.huangbaole.kotlinremote.demo.entiy.User
import com.sun.org.apache.xerces.internal.impl.dv.xs.YearMonthDV
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.assertj.core.api.Assertions.assertThat
import java.util.Calendar
import java.util.Date

@RunWith(SpringRunner::class)
@SpringBootTest
class UserRepositoryTests {
  @Autowired
  private lateinit var userRepository: UserRepository
  @Autowired
  private lateinit var babyRepository: BabyRepository

  @Before
  fun clear() {
    userRepository.deleteAll()
  }

  @Test
  fun testUser() {
    var user = User(username = "Tom", phone = "13300980909", address = "", gender = false,
        avatar = "", mark = "123")
    userRepository.save(user)
    assertThat(userRepository.count()).isEqualTo(1)
    var userSave = userRepository.findById(1).get()
    assertThat(userSave.username).isEqualTo("Tom")
    userSave.username = "Tom2"
    userSave.phone = "123"
    userRepository.save(userSave)
    var userTom = userRepository.findById(1).get()
    assertThat(userTom.username).isEqualTo("Tom2")
    assertThat(userTom.phone).isEqualTo("123")
    var user2 = User(username = "Jerry", phone = "13300980909", address = "", gender = false,
        avatar = "", mark = "123")
    var user3 = User(username = "Tom Son", phone = "13300980909", address = "", gender = false,
        avatar = "", mark = "123")
    userRepository.save(user2)
    userRepository.save(user3)
    var users = userRepository.findSimpleUserInfo()
    assertThat(users).isNotEmpty
    for (parent in users!!) {
      assertThat(parent.mark).isNullOrEmpty()
    }
    var contains = userRepository.findByUsernameContainsIgnoringCase(
        "tom")
    assertThat(contains).isNotEmpty
    for (parent in contains!!) {
      assertThat(parent.username).containsIgnoringCase("tom")
    }
  }
  @Test
  fun testBaby(){
    var baby=Baby(name = "HAHA son",nickname = "tt",age = 3,gender = true)
    var parent=User(username = "haha", phone = "13300980909", address = "", gender = false,
        avatar = "", mark = "123",babies = mutableListOf(baby))
    userRepository.save(parent)
    var babies = babyRepository.findSimpleBabies()
    assertThat(babies).isNotNull
    assertThat(babies?.size).isEqualTo(1)
    var hahason = babyRepository.findById(1).get()
    hahason.nickname="hh"
    babyRepository.save(hahason)

    var baby2=Baby(name = "HAHA girl",nickname = "tt",age = 4,gender = false)
    var hahas = userRepository.findByUsernameContainsIgnoringCase(
        "haha")
    hahas!![0].babies.add(baby2)
    var hahaId=hahas[0].id
    userRepository.save(hahas[0])

    var haha = userRepository.findById(hahaId).get()

    assertThat(haha.babies.size).isEqualTo(2)
    assertThat(haha.babies[0].nickname).isEqualTo("hh")
    assertThat(haha.babies[1].name).isEqualTo("HAHA girl")

    haha.babies.removeAt(1)

    userRepository.save(haha)

    assertThat(haha.babies.size).isEqualTo(1)



  }
}