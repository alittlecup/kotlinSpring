package cn.huangbaole.kotlinremote.demo

import cn.huangbaole.kotlinremote.demo.entiy.Baby
import cn.huangbaole.kotlinremote.demo.entiy.Card
import cn.huangbaole.kotlinremote.demo.entiy.CardKind.ALL_WEEK
import cn.huangbaole.kotlinremote.demo.entiy.CardKind.MIDWEEK
import cn.huangbaole.kotlinremote.demo.entiy.CardType
import cn.huangbaole.kotlinremote.demo.entiy.CourseType
import cn.huangbaole.kotlinremote.demo.entiy.User
import cn.huangbaole.kotlinremote.demo.repository.BabyRepository
import cn.huangbaole.kotlinremote.demo.repository.CardRepository
import cn.huangbaole.kotlinremote.demo.repository.CardTypeRepository
import cn.huangbaole.kotlinremote.demo.repository.CourseRepository
import cn.huangbaole.kotlinremote.demo.repository.CourseTypeRepository
import cn.huangbaole.kotlinremote.demo.repository.UserRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class UserRepositoryTest {
  @Autowired
  private lateinit var userRepository: UserRepository
  @Autowired
  private lateinit var babyRepository: BabyRepository
  @Autowired
  private lateinit var courseTypeRepository: CourseTypeRepository

  @Autowired
  private lateinit var courseRepository: CourseRepository

  @Autowired
  private lateinit var cardTypeRepository: CardTypeRepository

  @Autowired
  private lateinit var cardRepository: CardRepository


  @Test
  fun testUser() {
    userRepository.deleteAll()
    babyRepository.deleteAll()
    var user = User(username = "Tom", gender = false, avatar = "", phone = "13300980989", state = 0,
        address = "", mark = "鱼子介绍")
    userRepository.save(user)

  }

  @Test
  fun testCard() {
//    var findByUsernameLike = userRepository.findByUsernameLike("Tom")
  }

  private fun addBaby(id: Long) {

  }

  @Test
  fun testBaby() {
    babyRepository.deleteAll()
    var user = userRepository.findById(1).get()
//    var baby = Baby(name = "TomS", avator = "", age = 5, gender = false, nickname = "tt")
    var save1 = userRepository.save(user)
    print(save1)
  }

  @Test
  fun testCourseType() {
    courseTypeRepository.deleteAll()
    var courseTypeNine = CourseType(name = "九点半早课", duration = 30, avator = "",
        desc = "九点半上的小孩子课，用于一岁以下的孩子")
    var courseTypeTen = CourseType(name = "十点早课", duration = 40, avator = "",
        desc = "十点上的小孩子课，用于一岁到三岁以下的孩子")
    var courseTypeTenFour = CourseType(name = "十点四十早课", duration = 40, avator = "",
        desc = "十点四十上的小孩子课，用于一岁到三岁以下的孩子")
    var courseTypeEle = CourseType(name = "十一点二十午课", duration = 60, avator = "",
        desc = "十一点的小孩子课，用于三岁以上的孩子")
    var courseType12 = CourseType(name = "十二点二十午课", duration = 60, avator = "",
        desc = "十二点的小孩子课，用于三岁以上的孩子")


    var courseType3 = CourseType(name = "下午三点午课", duration = 40, avator = "",
        desc = "三点的小孩子课，用于一岁到三岁以下的孩子")
    var courseType340 = CourseType(name = "下午三点四十午课", duration = 40, avator = "",
        desc = "三点四十的小孩子课，用于一岁到三岁以下的孩子")
    var courseType420 = CourseType(name = "下午四点二十午课", duration = 60, avator = "",
        desc = "四点二十的小孩子课，用于三岁以上的孩子")
    var courseType520 = CourseType(name = "下午五点二十午课", duration = 60, avator = "",
        desc = "五点二十的小孩子课，用于三岁以上的孩子")
    var courseType620 = CourseType(name = "下午六点二十午课", duration = 60, avator = "",
        desc = "六点二十的小孩子课，用于三岁以上的孩子")
    var courses = arrayListOf<CourseType>(courseTypeNine, courseTypeTen, courseTypeTenFour,
        courseTypeEle, courseType12, courseType3, courseType340, courseType420, courseType520,
        courseType620)
    courseTypeRepository.saveAll(courses)
    print(courseTypeRepository.findAll())
  }

  @Test
  fun textCardType() {
    cardTypeRepository.deleteAll()
    var cardType241 = CardType(name = "24次周中卡", type = MIDWEEK, count = 24, price = 7600,
        limit_time = 8 * 31, desc = "")
    var cardType242 = CardType(name = "24次任意卡", type = ALL_WEEK, count = 24, price = 7600,
        limit_time = 8 * 31, desc = "")
    var cardType481 = CardType(name = "48次周中卡", type = MIDWEEK, count = 48, price = 7600,
        limit_time = 8 * 31, desc = "")
    var cardType482 = CardType(name = "48次任意卡", type = ALL_WEEK, count = 48, price = 7600,
        limit_time = 8 * 31, desc = "")
    var cardType661 = CardType(name = "66次周中卡", type = MIDWEEK, count = 66, price = 7600,
        limit_time = 8 * 31, desc = "")
    var cardType662 = CardType(name = "66次任意卡", type = ALL_WEEK, count = 66, price = 7600,
        limit_time = 8 * 31, desc = "")
    var cardType961 = CardType(name = "96次周中卡", type = MIDWEEK, count = 96, price = 7600,
        limit_time = 8 * 31, desc = "")
    var cardType962 = CardType(name = "96次任意卡", type = ALL_WEEK, count = 96, price = 7600,
        limit_time = 8 * 31, desc = "")

    var cardtypes = arrayListOf<CardType>(cardType241, cardType242, cardType481, cardType482,
        cardType661, cardType662, cardType961, cardType962)
    cardTypeRepository.saveAll(cardtypes)
    print(cardTypeRepository.findAll())
  }
}