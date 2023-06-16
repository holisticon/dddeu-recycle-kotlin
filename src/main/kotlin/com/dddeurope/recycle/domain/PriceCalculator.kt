package com.dddeurope.recycle.domain

import com.dddeurope.recycle.events.*


class PriceCalculator(vararg events: Event) {

    init {
        events.forEach {
            handle(it)
        }
    }

    private fun handle(event: Event) {
        when (event) {
            is IdCardRegistered -> {
                // do something with cardRegistered
            }

            is IdCardScannedAtEntranceGate -> {
                // do something with cardScanned
            }

            is FractionWasDropped -> {
                // do something with fractionDropped
            }

            is IdCardScannedAtExitGate -> {
                // do something with cardRegistered
            }
        }
    }

    fun calculatePrice(cardId: String): Double {
        return 0.0
    }
}
