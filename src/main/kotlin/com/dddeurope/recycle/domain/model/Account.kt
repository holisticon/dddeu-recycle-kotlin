package com.dddeurope.recycle.domain.model

import mu.*
import java.time.*
import java.util.*

class Account(val cardId: CardId, val personId: PersonId, val address: Address, val city: City) {

    var openVisit: OpenVisit? = null
    val closedVisits = mutableListOf<ClosedVisit>()

    companion object : KLogging()

    fun openVisit(visitDate: VisitDate): Account {
        this.closeVisit()
        openVisit = OpenVisit(
            VisitId(UUID.randomUUID()),
            cardId,
            visitDate,
        )
        return this
    }

    fun closeVisit(): Account {
        if (openVisit != null) {
            closedVisits.add(openVisit!!.close())
            openVisit = null
        }

        return this
    }

    fun getDroppedFractions(year: Year): Map<FractionType, Weight> =
        FractionType.values()
            .associateWith { getDroppedWasteForYearAndFractionType(it, year) }

    private fun getDroppedWasteForYearAndFractionType(fractionType: FractionType, year: Year) =
        Weight(
            closedVisits
                .filter { it.visitDate.visitDate.year == year.value }
                .flatMap { closedVisit ->
                    closedVisit.droppedFractions
                        .filter { fraction -> fraction.fractionType == fractionType }
                        .map { fraction -> fraction.weight.weight }
                }.sum(),
        )

    override fun toString(): String {
        return "Account(cardId=$cardId, personId=$personId, address=$address, city=$city, openVisit=$openVisit, closedVisits=$closedVisits)"
    }
}
