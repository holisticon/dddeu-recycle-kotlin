package com.dddeurope.recycle.domain.service

import com.dddeurope.recycle.domain.model.*

interface AccountRepository {

    fun save(account: Account): Account
    fun findByCardId(cardId: CardId): Account?

    fun findAllByAddress(address: Address): List<Account>

    fun clean()
}
