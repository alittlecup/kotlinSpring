package cn.huangbaole.kotlinremote.demo.service

import cn.huangbaole.kotlinremote.demo.entiy.Staff
import cn.huangbaole.kotlinremote.demo.entiy.StaffPosition.TRAINER
import cn.huangbaole.kotlinremote.demo.exception.SimpleException
import cn.huangbaole.kotlinremote.demo.repository.StaffRepostory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component("staffService")
@Transactional
class StaffServiceImpl : StaffService {
  override fun findOne(id: Long): Staff {
    return staffRepository.findById(id).get()
  }

  @Autowired
  private lateinit var staffRepository: StaffRepostory

  override fun findTrainer(): List<Staff>? {
    return staffRepository.findByPositionIs(staffPosition = TRAINER)
  }

  override fun findAllStaffByName(name: String): List<Staff>? {
    return if (name.isNullOrEmpty()) {
      staffRepository.findAll()
    } else {
      staffRepository.findByNameContainsIgnoringCase(name)
    }
  }

  override fun add(t: Staff): Staff {
    var checkSelf = t.checkSelf()
    if (checkSelf.isNullOrEmpty()) {
      throw SimpleException(checkSelf)
    } else {
      return staffRepository.save(t)
    }
  }

  override fun update(id: Long, t: Staff): Staff {
    var exist = staffRepository.existsById(id)
    if (t.checkSelf().isNullOrEmpty()) {
      throw SimpleException(t.checkSelf())
    }
    return if (exist) {
      t.id = id
      staffRepository.save(t)
    } else {
      staffRepository.save(t)
    }
  }

  override fun delete(id: Long) {
    if (staffRepository.existsById(id)) {
      staffRepository.deleteById(id)
    } else {
      throw SimpleException("找不到此用户")
    }
  }

  override fun findAll(): List<Staff>? {
    return staffRepository.findAll()
  }
}