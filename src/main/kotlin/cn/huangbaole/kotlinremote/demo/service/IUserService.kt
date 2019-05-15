package cn.huangbaole.kotlinremote.demo.service

import cn.huangbaole.kotlinremote.demo.entiy.User


interface IUserService {
  fun saveUser(user: User):User
  fun findUser(id: Long):User
  fun deleteUser(id: Long):Boolean
  fun loadUsers():List<User>
}