package cn.huangbaole.kotlinremote.demo.controller

import cn.huangbaole.kotlinremote.demo.entiy.CardType
import cn.huangbaole.kotlinremote.demo.http.Result
import cn.huangbaole.kotlinremote.demo.service.CardService
import cn.huangbaole.kotlinremote.demo.service.CardTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/card")
class CardController {

    @Autowired
    private lateinit var cardTypeService: CardTypeService
    @Autowired
    private lateinit var cardService: CardService

    @GetMapping("/type/all")
    fun getAllCardTypes(): Result<List<CardType>?> {
        return Result.success(cardTypeService.findAll())
    }

    @PostMapping("/type/")
    fun postCardType(@RequestBody cardType: CardType): Result<CardType> {
        return Result.success(cardTypeService.add(cardType))
    }

    @PutMapping("/type/{id}")
    fun putCardType(@PathVariable(name = "id", required = true) id: Long, @RequestBody cardType: CardType): Result<CardType> {
        return Result.success(cardTypeService.update(id, cardType))
    }

    @DeleteMapping("/type/{id}")
    fun deleteCardType(@PathVariable(name = "id", required = true) id: Long): Result<Boolean> {
        cardTypeService.delete(id)
        return Result.success(true)
    }

}