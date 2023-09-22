package com.dddeurope.recycle.application

import com.dddeurope.recycle.domain.model.*
import com.dddeurope.recycle.domain.service.*
import org.springframework.stereotype.*

@Component
class DropFraction(
    private val accountRepository: AccountRepository,
) {

    fun dropFraction(cardId: CardId, fraction: Fraction) =
        accountRepository.findByCardId(cardId)!!.apply { openVisit!!.dropFraction(fraction) }
            .let { accountRepository.save(it) }
}
