package com.dddeurope.recycle.domain

import com.dddeurope.recycle.events.*
import java.util.*


class PriceCalculator(events: List<Event>) {
    constructor(vararg events: Event) : this(Arrays.stream<Event>(events).toList())

    init {
        for (event in events) handle(event)
    }

    private fun handle(event: Event) {
        if (event is IdCardRegistered) {
            // do something with cardRegistered
        }
        if (event is IdCardScannedAtEntranceGate) {
            // do something with cardScanned
        }
        if (event is FractionWasDropped) {
            // do something with fractionDropped
        }
        if (event is IdCardScannedAtExitGate) {
            // do something with cardRegistered
        }
    }

    fun calculatePrice(cardId: String): Double {
        return 0.0
    }
}
