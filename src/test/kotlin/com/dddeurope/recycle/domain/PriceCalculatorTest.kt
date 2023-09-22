package com.dddeurope.recycle.domain

import com.dddeurope.recycle.domain.service.*
import com.dddeurope.recycle.infrastructure.events.*
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.*


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