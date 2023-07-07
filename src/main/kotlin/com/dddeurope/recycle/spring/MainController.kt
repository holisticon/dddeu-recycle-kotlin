package com.dddeurope.recycle.spring

import com.dddeurope.recycle.domain.PriceCalculator
import com.dddeurope.recycle.events.EventMessage
import com.dddeurope.recycle.events.PriceWasCalculated
import mu.KLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/")
class MainController {
    companion object : KLogging()

    @GetMapping("/")
    fun home(): String =
        "please enter a public URL to this site on https://domainmodelling.dev, as specified in the readme"

    @GetMapping("/validate")
    fun validate(): String = "Hi!"

    @PostMapping("/handle-command")
    fun handle(@RequestBody request: RecycleRequest): ResponseEntity<EventMessage> {
        logger.info("Incoming Request: {}", request.asString())

        // If you have no inspiration to start implementing, uncomment this part:
        //        val calculator = PriceCalculator(*request.getEvents().toTypedArray())
        //        val amount = request.toCalculatePriceCommand().let {
        //            calculator.calculatePrice(it.cardId)
        //        }
        //
        //        return ResponseEntity.ok(
        //            EventMessage(
        //                eventId = UUID.randomUUID().toString(),
        //                payload = PriceWasCalculated("123", amount,  "EUR")
        //            )
        //        )

        return ResponseEntity.ok(
            EventMessage(
                eventId = UUID.randomUUID().toString(),
                payload = PriceWasCalculated("123", 1.0, "EUR")
            )
        )
    }
}