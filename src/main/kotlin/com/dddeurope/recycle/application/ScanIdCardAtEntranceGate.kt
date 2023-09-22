package com.dddeurope.recycle.application

import com.dddeurope.recycle.domain.model.*
import com.dddeurope.recycle.domain.service.*
import org.springframework.stereotype.*

@Component
class ScanIdCardAtEntranceGate(
    private val accountRepository: AccountRepository,
) {

    fun scanIdCardAtEntranceGate(
        cardId: CardId,
        visitDate: VisitDate,
    ) {
        accountRepository.findByCardId(cardId)!!.apply {
            this.openVisit(visitDate)
        }
            .let { accountRepository.save(it) }
    }
}
