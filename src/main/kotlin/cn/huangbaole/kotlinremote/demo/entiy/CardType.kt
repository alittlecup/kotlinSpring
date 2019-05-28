package cn.huangbaole.kotlinremote.demo.entiy

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "t_card_type")
data class CardType(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long = -1,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    var type: CardKind?,
    @Column(nullable = false)
    var count: Int,
    @Column(nullable = false)
    var price: Int,
    @Column(nullable = false)
    var limit_time: Int,
    @Column(nullable = false)
    var fdesc: String? = null
) {
  fun checkSelf(): String {
    return when {
      name.isNullOrEmpty() -> "会员卡名称不能为空"
      type == null -> "会员卡类型能为空"
      count >= 0 -> "次数不能小于 0"
      limit_time <= 0 -> "有效期应大于 0 天"
      else -> ""
    }
  }
}

enum class CardKind {
  MIDWEEK, ALL_WEEK
}