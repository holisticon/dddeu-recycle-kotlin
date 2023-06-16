package com.dddeurope.recycle.spring

import com.dddeurope.recycle.command.CalculatePrice
import com.dddeurope.recycle.command.CommandMessage
import com.dddeurope.recycle.events.Event
import com.dddeurope.recycle.events.EventMessage
import com.dddeurope.recycle.events.PriceWasCalculated
import mu.KLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
        // PriceCalculator calculator = new PriceCalculator(eventsOf(request));
        // CalculatePrice command = commandOf(request);
        // double amount = calculator.calculatePrice(command.cardId());
        // return ResponseEntity.ok(
        //     EventMessage(
        //         eventId = UUID.randomUUID().toString(),
        //         payload = PriceWasCalculated("123", 1.0, "EUR")
        //     )
        // )
        return ResponseEntity.ok(
            EventMessage(
                eventId = UUID.randomUUID().toString(),
                payload = PriceWasCalculated("123", 1.0, "EUR")
            )
        )
    }

    private fun eventsOf(request: RecycleRequest): List<Event> = request.history
        .map { it.payload }

    private fun commandOf(request: RecycleRequest): CalculatePrice = request.command.payload as CalculatePrice

    data class RecycleRequest(
        val history: List<EventMessage>,
        val command: CommandMessage
    ) {

        fun asString(): String {
            val historyAsString = history.joinToString("\n\t") { it.toString() }

            return String.format("%n%s %nWith History\n\t%s", command, historyAsString)
        }
    }

}