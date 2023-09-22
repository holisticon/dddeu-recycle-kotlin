package com.dddeurope.recycle.infrastructure.spring

import com.dddeurope.recycle.infrastructure.command.*
import com.dddeurope.recycle.infrastructure.events.*

data class RecycleRequest(
    val history: List<EventMessage>,
    val command: CommandMessage
) {

    fun asString(): String {
        val historyAsString = history.joinToString("\n\t") { it.toString() }

        return String.format("%n%s %nWith History\n\t%s", command, historyAsString)
    }

    fun getEvents() = this.history.map { it.payload }

    fun toCalculatePriceCommand() = this.command.payload as CalculatePrice
}