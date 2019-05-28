package cn.huangbaole.kotlinremote.demo.service

import cn.huangbaole.kotlinremote.demo.entiy.Baby
import cn.huangbaole.kotlinremote.demo.entiy.User
import cn.huangbaole.kotlinremote.demo.exception.SimpleException
import cn.huangbaole.kotlinremote.demo.repository.BabyRepository
import cn.huangbaole.kotlinremote.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component("userService")
@Transactional
class UserServiceImpl : UserService {
  override fun findOne(id: Long): User {
    return userRepository.findById(id).get()
  }

  @Autowired
  private lateinit var userRepository: UserRepository
  @Autowired
  private lateinit var babyRepository: BabyRepository

  override fun findUserByName(name: String): List<User>? {
    return userRepository.findByUsernameContainsIgnoringCase(name)
  }

  override fun add(t: User): User {
    if (!t.checkSelf().isNullOrEmpty()) {
      throw SimpleException(t.checkSelf())
    }
    return userRepository.save(t)
  }

  override fun update(id: Long, t: User): User {
    var exist = userRepository.existsById(id)
    if (!t.checkSelf().isNullOrEmpty()) {
      throw SimpleException(t.checkSelf())
    }
    return if (exist) {
      t.id = id
      userRepository.save(t)
    } else {
      userRepository.save(t)
    }
  }

  override fun delete(id: Long) {
    var existsById = userRepository.existsById(id)
    if (existsById) {
      userRepository.deleteById(id)
    } else {
      throw SimpleException("not found user by id")

    }
  }

  override fun findAll(): List<User>? {
    return userRepository.findSimpleUserInfo()
  }
}

@Component("babyService")
@Transactional
class BabyServiceImpl : BabyService {
  override fun delBaby(userId: Long, babyId: Long): Boolean {
    var removeResult = false
    var existsById = userRepository.existsById(userId)
    if (existsById) {
      var user = userRepository.findById(userId).get()
      for (baby in user.babies) {
        if (baby.id == babyId) {
          removeResult = user.babies.remove(baby)
          userRepository.save(user)
          break
        }
      }
      return removeResult
    } else {
      throw SimpleException("not found user by id")
    }
  }

  override fun findByParentId(parentId: Long): List<Baby> {
    return userRepository.findById(parentId).get().babies
  }

  override fun findOne(id: Long): Baby {
    return babyRepository.findById(id).get()
  }

  override fun addBaby(userId: Long, baby: Baby): Baby {
    var existsById = userRepository.existsById(userId)
    if (existsById) {
      var user = userRepository.findById(userId).get()
      if (baby.check().isNullOrEmpty()) {
        user.babies.add(baby)
        var save = userRepository.save(user)
        return save.babies.last()
      } else {
        throw SimpleException(baby.check())
      }
    } else {
      throw SimpleException("not found user by id")
    }
  }

  @Autowired
  private lateinit var babyRepository: BabyRepository
  @Autowired
  private lateinit var userRepository: UserRepository

  override fun findBabyByName(name: String): List<Baby>? {
    return babyRepository.findByNameContainsIgnoringCase(name)
  }

  override fun add(t: Baby): Baby {
    throw SimpleException("不能使用此方法添加宝贝")
  }

  override fun update(id: Long, t: Baby): Baby {
    var exist = babyRepository.existsById(id)
    if (!t.check().isNullOrEmpty()) {
      throw SimpleException(t.check())
    }
    return if (exist) {
      t.id = id
      babyRepository.save(t)
    } else {
      babyRepository.save(t)
    }
  }

  override fun delete(id: Long) {
    throw SimpleException("不能使用此方法删除宝贝")
  }

  override fun findAll(): List<Baby>? {
    return babyRepository.findSimpleBabies()
  }

}
