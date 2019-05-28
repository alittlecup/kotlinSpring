package cn.huangbaole.kotlinremote.demo

import cn.huangbaole.kotlinremote.demo.entiy.User
import cn.huangbaole.kotlinremote.demo.service.UserService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class UserServiceTest {
  @Autowired
  lateinit var userService: UserService
  @Test
  fun addUser(){
    var findOne = userService.findOne(2)
    print(findOne)
  }
}