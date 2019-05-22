package cn.huangbaole.kotlinremote.demo.entiy

import java.io.Serializable
import java.util.Date
import javax.persistence.CascadeType
import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.FetchType.LAZY
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Transient

@Entity
@Table(name = "t_user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1,
    @Column(nullable = false)
    var username: String? = null,
    @Column(nullable = false)
    var gender: Boolean = true,
    @Column(nullable = true)
    var avatar: String? = null,
    @Column(nullable = false)
    var phone: String? = null,
    @Column(nullable = false)
    var createTime: Date = Date(),
    @Column(nullable = false)
    var state: Int = 0,
    @Column(nullable = true)
    var address: String? = null,
    @Column(nullable = true)
    var mark: String? = null,

    @OneToMany(cascade = [ALL], mappedBy = "parent")
    var babies: List<Baby>? = null,
    @OneToMany(cascade = [ALL], mappedBy = "user")
    var cards: List<Card>? = null
)
