package cn.huangbaole.kotlinremote.demo.service.impl

import cn.huangbaole.kotlinremote.demo.entiy.User
import cn.huangbaole.kotlinremote.demo.repository.UserRepository
import cn.huangbaole.kotlinremote.demo.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService : IUserService {
  @Autowired
  lateinit var userRepository: UserRepository

  override fun saveUser(user: User): User = userRepository.save(user)

  override fun findUser(id: Long): User = userRepository.getOne(id)

  override fun deleteUser(id: Long): Boolean {
    var existsById = userRepository.existsById(id)
    return if (existsById) {
      userRepository.deleteById(id)
      true
    } else {
      false
    }
  }


  override fun loadUsers(): List<User> = userRepository.findAll()


}