package cn.huangbaole.kotlinremote.demo.entiy

import com.sun.xml.internal.bind.util.Which
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
    @OneToMany(cascade = [ALL])
    var babies: MutableList<Baby> = mutableListOf(),
    @OneToMany(cascade = [ALL])
    var cards: MutableList<Card> = mutableListOf()
) {
  fun checkSelf(): String {
    return when {
      username.isNullOrEmpty() -> "用户名为空"
      phone.isNullOrEmpty() -> "联系方式为空"
      else -> ""
    }
  }

  constructor(id: Long, username: String?, avatar: String?, phone: String?, state: Int) : this(){
    this.id=id
    this.username=username
    this.avatar=avatar
    this.phone=phone
    this.state=state
  }
}
