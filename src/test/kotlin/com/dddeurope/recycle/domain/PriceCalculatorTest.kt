package com.dddeurope.recycle.domain

import com.dddeurope.recycle.events.IdCardRegistered
import com.dddeurope.recycle.events.IdCardScannedAtEntranceGate
import com.dddeurope.recycle.events.IdCardScannedAtExitGate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class PriceCalculatorTest {
    @Test
    fun visitWithoutDroppingWaste() {
        val calculator = PriceCalculator(
            IdCardRegistered("123", "John Doe", "an address", "a street"),
            IdCardScannedAtEntranceGate("123", "2023-01-01"),
            IdCardScannedAtExitGate("123")
        )
        assertThat(calculator.calculatePrice("123")).isEqualTo(0.0)
    }
}