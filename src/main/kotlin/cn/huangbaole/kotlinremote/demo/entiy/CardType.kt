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
@Table(name = "t_card_clazz")
data class CardType(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long = -1,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    var type: CardKind,
    @Column(nullable = false)
    var count: Int,
    @Column(nullable = false)
    var price: Int,
    @Column(nullable = false)
    var limit_time: Int,
    @Column(nullable = true)
    var desc: String? = ""
)

enum class CardKind {
  MIDWEEK, ALL_WEEK
}