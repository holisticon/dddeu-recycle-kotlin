package com.dddeurope.recycle.infrastructure.spring

import com.dddeurope.recycle.application.*
import com.dddeurope.recycle.domain.*
import com.dddeurope.recycle.domain.model.*
import com.dddeurope.recycle.domain.service.*
import com.dddeurope.recycle.infrastructure.events.*
import mu.*
import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import java.time.*
import java.util.*

@RestController
@RequestMapping("/")
class MainController(
    private val registerIdCard: RegisterIdCard,
    private val scanIdCardAtEntranceGate: ScanIdCardAtEntranceGate,
    private val dropFraction: DropFraction,
    private val scanIdCardAtExitGate: ScanIdCardAtExitGate,
    private val calculatePrice: CalculatePrice,
) {
    companion object : KLogging()

    @GetMapping("/")
    fun home(): String =
        "please enter a public URL to this site on https://domainmodelling.dev, as specified in the readme"

    @GetMapping("/validate")
    fun validate(): String = "Hi!"

    @PostMapping("/handle-command")
    fun handle(@RequestBody request: RecycleRequest): ResponseEntity<EventMessage> {
        logger.info("Incoming Request: {}", request.asString())

        request.getEvents().toTypedArray<Event>().forEach { event ->
            when (event) {
                is IdCardRegistered -> {
                    registerIdCard.registerIdCard(
                        CardId(event.cardId),
                        PersonId(event.personId),
                        Address(event.address),
                        City.cityForCity(event.city),
                    )
                }

                is IdCardScannedAtEntranceGate -> {
                    scanIdCardAtEntranceGate.scanIdCardAtEntranceGate(
                        CardId(event.cardId),
                        VisitDate(LocalDate.parse(event.date)),
                    )
                }

                is FractionWasDropped -> {
                    dropFraction.dropFraction(
                        CardId(event.cardId),
                        Fraction(
                            FractionType.findByType(event.fractionType)!!,
                            Weight(event.weight),
                        ),
                    )
                }

                is IdCardScannedAtExitGate -> {
                    scanIdCardAtExitGate.scanIdCardAtExitGate(CardId(event.cardId))
                }
            }
        }

        val command = request.toCalculatePriceCommand()
        val amount = calculatePrice.calculatePrice(CardId(command.cardId))

        return ResponseEntity.ok(
            EventMessage(
                eventId = UUID.randomUUID().toString(),
                payload = PriceWasCalculated(command.cardId, amount.price, "EUR"),
            ),
        )
    }
}
