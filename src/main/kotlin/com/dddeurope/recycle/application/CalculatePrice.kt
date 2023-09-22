package com.dddeurope.recycle.application

import com.dddeurope.recycle.domain.model.*
import com.dddeurope.recycle.domain.service.*
import mu.*
import org.springframework.stereotype.*
import java.time.*

@Component
class CalculatePrice(
    val accountRepository: AccountRepository,
) {
    companion object : KLogging()

    fun calculatePrice(cardId: CardId): Price {
        val account = accountRepository.findByCardId(cardId) ?: throw IllegalStateException("No account for id $cardId")

        val allByAddress = accountRepository.findAllByAddress(account.address)
        allByAddress.filter { it.cardId != cardId }.forEach { it.closeVisit() }
        logger.error { "allByAddress: $allByAddress" }
        val droppedFractions = allByAddress
            .map { it.getDroppedFractions(Year.of(account.openVisit!!.visitDate.visitDate.year)) }
            .onEach { logger.error { "DroppedWeight per fractionType: $it" } }
            .flatMap { it.asSequence() }
            .groupBy({ it.key }, { it.value })
            .onEach { logger.error { "DroppedWeight per fractionType: $it" } }
            .mapValues { Weight(it.value.sumOf { it.weight }) }
        logger.error { "droppedFractions: $droppedFractions" }

        val remainingExemptions = getRemainingExemptions(droppedFractions, account.city)

        val weightsToPay = getWeightsToPay(account.openVisit!!, remainingExemptions)
        val priceToPay = Price(
            weightsToPay
                .map { calculateFractionPrice(it.value, account.city.getFractionRate(it.key)) }
                .sumOf { it.price },
        )

        account.closeVisit().let { accountRepository.save(it) }
        accountRepository.clean()

        return priceToPay
    }

    private fun getRemainingExemptions(
        droppedFractions: Map<FractionType, Weight>,
        city: City,
    ): Map<FractionType, Weight> = droppedFractions
        .map { it.key to (city.getExemptionFor(it.key)).minus(it.value) }
        .toMap()

    private fun getWeightsToPay(visit: OpenVisit, remainingExemptions: Map<FractionType, Weight>): Map<
        FractionType,
        Weight,
        > =
        visit.droppedFractions.associate {
            it.fractionType to it.weight.minus(
                remainingExemptions[
                    it
                        .fractionType,
                ]!!,
            )
        }
}
