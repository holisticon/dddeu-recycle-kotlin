package com.dddeurope.recycle.spring

import com.dddeurope.recycle.command.CalculatePrice
import com.dddeurope.recycle.command.CommandMessage
import com.dddeurope.recycle.events.EventMessage

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