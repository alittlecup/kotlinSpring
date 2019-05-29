package cn.huangbaole.kotlinremote.demo.entiy

import java.util.*
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "t_card")
data class Card(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        var id: Long = -1,
        @OneToOne
        @JoinColumn(name = "card_type_id")
        var cardType: CardType?,
        @Column(nullable = false)
        var createDate: Date = Date(),
        @Column(nullable = false)
        var startDate: Date? = null,
        @Column(nullable = false)
        var endDate: Date? = null,
        @Column(nullable = false)
        var real_prices: Int,
        @Column(nullable = false)
        var real_count: Int,
        @Column(nullable = false)
        var balance: Int
) {
    fun checkSelf(): String {
        return when {
            cardType == null -> "卡种类不能为空"
            startDate == null -> "开始时间不能为空"
            endDate == null -> "结束时间不能为空"
            real_count <= 0 -> "会员卡次数实收次数不能为空"
            else -> ""
        }
    }

    override fun toString(): String {
        return "Card(id=$id, cardType=$cardType, createDate=$createDate, startDate=$startDate, endDate=$endDate, real_prices=$real_prices, real_count=$real_count, balance=$balance)"
    }


}

@Entity
@Table(name = "t_card_charge")
data class CardChargeRecord(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        var id: Long = -1,
        @Column(nullable = false)
        var card_id: Long,
        @Column(nullable = false)
        var chargeBefore: Int,
        @Column(nullable = false)
        var chargeAfter: Int,
        @Column(nullable = false)
        var chargeCount: Int,
        @Column(nullable = false)
        @Enumerated(EnumType.ORDINAL)
        var chargeType: CardChargeType,
        @Column(nullable = false)
        var updateTime: Date = Date()
)

enum class CardChargeType {
    MAKE_CARD, STOP_CARD, TRAIN
}