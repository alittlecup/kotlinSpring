package cn.huangbaole.kotlinremote.demo.controller

import cn.huangbaole.kotlinremote.demo.entiy.Baby
import cn.huangbaole.kotlinremote.demo.entiy.User
import cn.huangbaole.kotlinremote.demo.http.Result
import cn.huangbaole.kotlinremote.demo.repository.BabyRepository
import cn.huangbaole.kotlinremote.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Date

@RestController
@RequestMapping("/user")
class UserController {
  @Autowired
  lateinit var userRepository: UserRepository
  @Autowired
  lateinit var babyRepository:BabyRepository


  @GetMapping("/all")
  fun getAllUsers(): Result<List<User>> {
    return Result.success(userRepository.findAll())
  }

  @GetMapping("/{id}")
  fun getUserInfo(@PathVariable(name = "id", required = true) id: Long): Result<*> {
    var existsById = userRepository.existsById(id)
    if (existsById) {
      var user = userRepository.findById(id)
//      var ships = userService.findShipByUserId(id)
//      var babys = mutableListOf<Baby>()
//      for (ship in ships) {
//        babys.add(userService.findBabyById(ship.babyId))
//      }
//      user.babys = babys
      return Result.success(user)
    } else {
      return Result.error("not found user by id")
    }
  }

  @PostMapping("/")
  fun addUser(user: User): Result<*> {
    var userVilible = isUserVilible(user)
    return if (userVilible.isNotEmpty()) {
      Result.error(userVilible)
    } else {
      user.createTime = Date()
      var save = userRepository.save(user)
      Result.success(save)
    }
  }

  @PutMapping("/{id}/")
  fun updateUser(@PathVariable(required = true, name = "id") id: Long, user: User): Result<*> {
    var exists = userRepository.existsById(id)
    return if (exists && isUserVilible(user).isEmpty()) {
      Result.success(userRepository.save(user))
    } else {
      Result.error("数据异常")
    }
  }

  @DeleteMapping("/{id}/")
  fun deleteUser(@PathVariable(name = "id", required = true) id: Long): Result<*> {
    var existsById = userRepository.existsById(id)
    return if (existsById) {
      userRepository.deleteById(id)
      Result.success()
    } else {
      Result.error("not found user by id ")
    }
  }

  @PostMapping("/{id}/baby/")
  fun addUserBaby(@PathVariable(name = "id", required = true) id: Long, baby: Baby): Result<*> {
    var exist = userRepository.existsById(id)
    if (!exist) {
      return Result.error("not found user by id")
    }
    return if (isBabyVilible(baby)) {
      var saveBaby = babyRepository.save(baby)
//      var ship = userBabyShipRepository.findByBabyIdAndUserId(baby.id, id)
//      if (ship != null) {
//        userBabyShipRepository.save(UserBabyShip(babyId = saveBaby.id, userId = id))
//      }
      Result.success(baby)
    } else {
      Result.error()
    }
  }

  @PutMapping("/{id}/baby/{babyId}/")
  fun updateBaby(@PathVariable(name = "id", required = true) id: Long, @PathVariable(
      name = "babyId", required = true) babyId: Long, baby: Baby): Result<*> {
    var userExist = userRepository.existsById(id)
    var babyExist = babyRepository.existsById(id)
//    var ship = userBabyShipRepository.findByBabyIdAndUserId(babyId, userId = id)
//    if (userExist && babyExist && ship != null && isBabyVilible(baby)) {
//      return Result.error(babyRepository.save(baby))
//    } else {
      return Result.error()
//    }
  }

  @DeleteMapping("/{id}/baby/{babyId}/")
  fun deleteBaby(@PathVariable(name = "id", required = true) id: Long, @PathVariable(
      name = "babyId", required = true) babyId: Long): Result<*> {
//    var userExist = userRepository.existsById(id)
//    var babyExist = babyRepository.existsById(id)
//    var ship = userBabyShipRepository.findByBabyIdAndUserId(babyId, userId = id)
//    if (userExist && babyExist && ship != null) {
//      babyRepository.deleteById(babyId)
//      userBabyShipRepository.deleteByBabyIdAndUserId(babyId, id)
//      return Result.success()
//    } else {
      return Result.error()

//    }
  }

  private fun isUserVilible(user: User): String {
    return when {
      StringUtils.isEmpty(user.username) -> "用户名为空"
      StringUtils.isEmpty(user.phone) -> "手机号为空"
      else -> ""
    }
  }

  private fun isBabyVilible(baby: Baby): Boolean {
    var b = baby.name.isNotEmpty() || baby.nickname.isNotEmpty()
    baby.createTime = Date()
    return b && baby.age > 0
  }
}
