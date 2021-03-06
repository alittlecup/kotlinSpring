package cn.huangbaole.kotlinremote.demo.repository

import cn.huangbaole.kotlinremote.demo.entiy.Card
import cn.huangbaole.kotlinremote.demo.entiy.CardType
import org.springframework.data.jpa.repository.JpaRepository

interface CardRepository : JpaRepository<Card, Long> {
  fun findByUser_Id(userId: Long): List<Card>?

}

interface CardTypeRepository : JpaRepository<CardType, Long> {
  fun findByCountAndType(count: Int, type: Int): CardType?
}