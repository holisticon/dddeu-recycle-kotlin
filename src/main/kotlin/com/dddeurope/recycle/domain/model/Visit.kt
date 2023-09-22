package com.dddeurope.recycle.domain.model

import java.time.*

abstract class Visit(
    open val visitId: VisitId,
    open val cardId: CardId,
    open val visitDate: VisitDate,
)

data class OpenVisit(override val visitId: VisitId, override val cardId: CardId, override val visitDate: VisitDate) :
    Visit(
        visitId,
        cardId,
        visitDate,
    ) {

    val droppedFractions: MutableList<Fraction> = mutableListOf()

    fun dropFraction(fraction: Fraction) {
        droppedFractions.add(fraction)
    }

    fun close(): ClosedVisit = ClosedVisit(this)
}

data class ClosedVisit private constructor(
    override val visitId: VisitId,
    override val cardId: CardId,
    override val visitDate: VisitDate,
) :
    Visit(
        visitId,
        cardId,
        visitDate,
    ) {

    constructor(openVisit: OpenVisit) : this(
        openVisit.visitId,
        openVisit.cardId,
        openVisit.visitDate,
    ) {
        this.droppedFractions = openVisit.droppedFractions
        closedAt = LocalDateTime.now()
    }

    var droppedFractions: MutableList<Fraction> = mutableListOf()
    lateinit var closedAt: LocalDateTime
    override fun toString(): String {
        return "ClosedVisit(visitId=$visitId, cardId=$cardId, visitDate=$visitDate, droppedFractions=$droppedFractions, closedAt=$closedAt)"
    }
}
