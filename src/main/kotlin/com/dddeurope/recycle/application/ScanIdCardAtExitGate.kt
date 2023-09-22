package com.dddeurope.recycle.application

import com.dddeurope.recycle.domain.model.*
import com.dddeurope.recycle.domain.service.*
import org.springframework.stereotype.*

@Component
class ScanIdCardAtExitGate(
    private val accountRepository: AccountRepository,
) {

    fun scanIdCardAtExitGate(cardId: CardId) {
        // do nothing
    }
}
