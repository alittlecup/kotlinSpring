package cn.huangbaole.kotlinremote.demo

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest
class ParentRepositoryTest {
  @Autowired
  private lateinit var parentRepository: ParentRepository

  @Autowired
  private lateinit var childRepository: ChildRepository
  @Autowired
  private lateinit var groupRepository: GroupRepository

  @Test
  fun updateParents() {
    parentRepository.save(Parent(name = "AAA"))
    parentRepository.save(Parent(name = "BBB"))
    parentRepository.save(Parent(name = "CCC"))
    parentRepository.save(Parent(name = "DDD"))
    parentRepository.save(Parent(name = "EEE"))
    parentRepository.save(Parent(name = "FFF"))
    var findAll = parentRepository.findAll()
    print(findAll)
  }

  @Test
  @Transactional
  fun addChilds() {
    var parentID = 2L
    var parent = parentRepository.findById(parentID).get()
    var child = Child(nickname = "little_bb", parent = parent)
    var child2 = Child(nickname = "little_bb2", parent = parent)
    childRepository.saveAll(arrayListOf(child, child2))

    var get = parentRepository.findById(parentID).get()
    print(get)
  }

  fun updateChild() {
    var childId = 2L
    var child = childRepository.findById(childId).get()
    child.nickname = "new nick name2"
    childRepository.save(child)
    var BLikeParents = parentRepository.findByNameLike("%B%")
    println(BLikeParents[0].childs)
  }

  @Test
  fun testMany() {
    var group = Group(name = "书法课")
    var group2 = Group(name = "游泳课")
    var save = groupRepository.saveAll(arrayListOf(group, group2))

    print(save)
    var child = childRepository.findById(2).get()
    child.groups = groupRepository.findAll()
    childRepository.save(child)
  }
}