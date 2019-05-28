package cn.huangbaole.kotlinremote.demo.service

interface BaseService<T> {
  fun add(t: T): T
  fun update(id: Long, t: T): T
  fun delete(id: Long)
  fun findAll(): List<T>?
  fun findOne(id:Long):T
}