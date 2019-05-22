package cn.huangbaole.kotlinremote.demo

import cn.huangbaole.kotlinremote.demo.entiy.Baby
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "t_parent")
data class Parent(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long = -1,
    @Column(nullable = false)
    var name: String,
    @OneToMany(cascade = [ALL], mappedBy = "parent")
    var childs: List<Child> = mutableListOf()
) {

}

@Entity
@Table(name = "t_child")
data class Child(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long = -1,
    @Column(nullable = false)
    var nickname: String,
    @ManyToOne(optional = false)
    @JoinColumn(name = "parent_id")
    var parent: Parent,
    @ManyToMany()
    @JoinTable(name = "child_group_inner",
        joinColumns = [JoinColumn(name = "child_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "group_id", referencedColumnName = "id")])
    var groups: List<Group> = mutableListOf()
) {
  override fun toString(): String {
    return "{child:{" +
        "name:$nickname,id: $id}}"
  }
}

@Entity
@Table(name = "t_group")
data class Group(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long = -1,
    @Column(nullable = false)
    var name: String,
    @ManyToMany(mappedBy = "groups")
    var childs: List<Child> = mutableListOf()
)

interface ParentRepository : JpaRepository<Parent, Long> {
  fun findByNameLike(name: String): List<Parent>
}

interface ChildRepository : JpaRepository<Child, Long> {


}

interface GroupRepository : JpaRepository<Group, Long> {
}