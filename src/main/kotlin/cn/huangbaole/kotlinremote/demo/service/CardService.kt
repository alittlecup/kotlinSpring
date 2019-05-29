package cn.huangbaole.kotlinremote.demo.service

import cn.huangbaole.kotlinremote.demo.entiy.Card
import cn.huangbaole.kotlinremote.demo.entiy.CardType
import cn.huangbaole.kotlinremote.demo.exception.SimpleException
import cn.huangbaole.kotlinremote.demo.repository.CardRepository
import cn.huangbaole.kotlinremote.demo.repository.CardTypeRepository
import cn.huangbaole.kotlinremote.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

interface CardTypeService : BaseService<CardType> {

}

interface CardService : BaseService<Card> {
    fun findCardByUserId(userId: Long): List<Card>?
    fun addCard(userId: Long, card: Card): Card
    fun delCard(userId: Long, cardId: Long): Boolean

}

@Component("cardService")
@Transactional
class CardServiceImpl : CardService {
    @Autowired
    private lateinit var cardRepository: CardRepository

    @Autowired
    private lateinit var userRepository: UserRepository


    override fun addCard(userId: Long, card: Card): Card {
        var user = userRepository.findById(userId).get()
        if(card.checkSelf().isNotEmpty()){
            throw SimpleException(card.checkSelf())
        }
        user.cards.add(card)
        var save = userRepository.save(user)
        var id = save.cards.last().id
        return cardRepository.findById(id).get()
    }

    override fun delCard(userId: Long, cardId: Long): Boolean {
        var user = userRepository.findById(userId).get()
        var removeResult=false
        for (card in user.cards){
            if(card.id== cardId){
               removeResult= user.cards.remove(card)
                break
            }
        }
        return removeResult
    }

    override fun findOne(id: Long): Card {
        return cardRepository.findById(id).get()
    }


    override fun add(t: Card): Card {
            throw SimpleException("should use addCard")
    }

    override fun update(id: Long, t: Card): Card {
        var existsById = cardRepository.existsById(id)
        if (existsById) {
            if (t.checkSelf().isNullOrEmpty()) {
                throw SimpleException(t.checkSelf())
            } else {
                t.id = id
                return cardRepository.save(t)
            }
        } else {
            return cardRepository.save(t)
        }
    }

    override fun delete(id: Long) {
            throw SimpleException("should use delCard")
    }

    override fun findAll(): List<Card>? {
        return cardRepository.findAll()
    }

    override fun findCardByUserId(userId: Long): List<Card>? {
        var existsById = userRepository.existsById(userId)
        if (existsById) {
            return userRepository.findById(userId).get().cards
        } else {
            throw SimpleException("未找到当前用户")
        }
    }

}

@Component("cardTypeService")
@Transactional
class CardTypeServiceImpl : CardTypeService {
    override fun findOne(id: Long): CardType {
        return cardTypeRepository.findById(id).get()
    }

    @Autowired
    private lateinit var cardTypeRepository: CardTypeRepository

    override fun add(t: CardType): CardType {
        if (t.checkSelf().isNullOrEmpty()) {
            throw SimpleException(t.checkSelf())
        } else {
            return cardTypeRepository.save(t)
        }
    }

    override fun update(id: Long, t: CardType): CardType {
        var existsById = cardTypeRepository.existsById(id)
        if (existsById) {
            if (t.checkSelf().isNullOrEmpty()) {
                throw SimpleException(t.checkSelf())
            } else {
                t.id = id
                return cardTypeRepository.save(t)
            }
        } else {
            return cardTypeRepository.save(t)
        }
    }

    override fun delete(id: Long) {
        var existsById = cardTypeRepository.existsById(id)
        if (existsById) {
            cardTypeRepository.deleteById(id)
        } else {
            throw SimpleException("not found cardType by id")
        }
    }

    override fun findAll(): List<CardType>? {
        return cardTypeRepository.findAll()
    }

}