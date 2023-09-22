package com.dddeurope.recycle.application

import com.dddeurope.recycle.domain.model.*
import com.dddeurope.recycle.domain.service.*
import org.springframework.stereotype.*

@Component
class RegisterIdCard(
    private val repository: AccountRepository,
) {
    fun registerIdCard(
        cardId: CardId,
        personId: PersonId,
        address: Address,
        city: City,
    ) {
        repository.save(Account(cardId, personId, address, city))
    }
}
