package cn.huangbaole.kotlinremote.demo.controller

import cn.huangbaole.kotlinremote.demo.entiy.Baby
import cn.huangbaole.kotlinremote.demo.entiy.User
import cn.huangbaole.kotlinremote.demo.http.Result
import cn.huangbaole.kotlinremote.demo.http.Result.Companion
import cn.huangbaole.kotlinremote.demo.repository.BabyRepository
import cn.huangbaole.kotlinremote.demo.repository.UserRepository
import cn.huangbaole.kotlinremote.demo.service.BabyService
import cn.huangbaole.kotlinremote.demo.service.UserService
import cn.huangbaole.kotlinremote.demo.service.UserServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Date

@RestController
@RequestMapping("/user")
class UserController {
  @Autowired
  lateinit var userService: UserService
  @Autowired
  lateinit var babyService: BabyService


  @GetMapping("/all")
  fun getAllUsers(): Result<List<User>?> {
    return Result.success(userService.findAll())
  }

  @GetMapping("/{id}")
  fun getUserInfo(@PathVariable(name = "id", required = true) id: Long): Result<User> {
    return Result.success(userService.findOne(id))
  }

  @GetMapping("/{id}/baby/")
  fun getUserBaby(@PathVariable(name = "id", required = true) id: Long): Result<List<Baby>> {
    var findOne = userService.findOne(id)
    return Result.success(findOne.babies)
  }

  @PostMapping("/")
  fun addUser(@RequestBody user: User): Result<User> {
    return Result.success(userService.add(user))
  }

  @PutMapping("/{id}/")
  fun updateUser(@PathVariable(required = true,
      name = "id") id: Long, @RequestBody user: User): Result<*> {
    return Result.success(userService.update(id, user))
  }

  @GetMapping("/{id}/baby/{babyid}")
  fun updateUser(@PathVariable(required = true, name = "id") id: Long, @PathVariable(
      required = true, name = "babyid") babyid: Long): Result<*> {
    return Result.success(babyService.findOne(id))
  }


  @DeleteMapping("/{id}/")
  fun deleteUser(@PathVariable(name = "id", required = true) id: Long): Result<*> {
    userService.delete(id)
    return Result.success()
  }

  @PostMapping("/{id}/baby/")
  fun addUserBaby(@PathVariable(name = "id",
      required = true) id: Long, @RequestBody baby: Baby): Result<Baby> {
    return Result.success(babyService.addBaby(id, baby))
  }

  @PutMapping("/{id}/baby/{babyId}/")
  fun updateBaby(@PathVariable(name = "id", required = true) id: Long, @PathVariable(
      name = "babyId", required = true) babyId: Long, @RequestBody baby: Baby): Result<Baby> {
    return Result.success(babyService.update(babyId, baby))
  }

  @DeleteMapping("/{id}/baby/{babyId}/")
  fun deleteBaby(@PathVariable(name = "id", required = true) id: Long, @PathVariable(
      name = "babyId", required = true) babyId: Long): Result<*> {
    babyService.delete(id)
    return Result.success()
  }
}
