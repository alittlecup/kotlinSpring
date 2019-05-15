package cn.huangbaole.kotlinremote.demo.service

interface IUserBabyShipService {
  fun saveBabyAsUserChild(babyId: Long, userId: Long)
  fun removeUserChild(babyId: Long,userId: Long)
}