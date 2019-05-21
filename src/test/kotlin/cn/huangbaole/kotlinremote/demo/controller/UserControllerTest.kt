package cn.huangbaole.kotlinremote.demo.controller

import cn.huangbaole.kotlinremote.demo.entiy.User
import com.alibaba.fastjson.JSON
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

@RunWith(SpringJUnit4ClassRunner::class)
@WebMvcTest(UserController::class)
class UserControllerTest {
  @Autowired
  private var mvc: MockMvc? = null

  @Test
  fun getAllUsers() {
  }

  @Test
  fun getUserInfo() {
  }

  @Test
  fun addUser() {
    var user = User(username = "Tom", phone = "13456780987", state = 0)
    var userStr=JSON.toJSONString(user)
    mvc!!.perform(MockMvcRequestBuilders.post("/").accept(MediaType.APPLICATION_JSON)
        .content(userStr))
        .andExpect(status().isOk)
        .andExpect(content().string(userStr))
  }

  @Test
  fun updateUser() {
  }

  @Test
  fun deleteUser() {
  }

  @Test
  fun addUserBaby() {
  }

  @Test
  fun updateBaby() {
  }

  @Test
  fun deleteBaby() {
  }
}
