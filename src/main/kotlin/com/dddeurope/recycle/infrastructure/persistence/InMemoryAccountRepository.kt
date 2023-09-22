package com.dddeurope.recycle.infrastructure.persistence

import com.dddeurope.recycle.domain.model.*
import com.dddeurope.recycle.domain.service.*
import org.springframework.stereotype.*
import java.util.concurrent.*

@Component
class InMemoryAccountRepository : AccountRepository {

    private var store: MutableMap<CardId, Account> = ConcurrentHashMap()

    override fun save(account: Account): Account {
        store[account.cardId] = account
        return account
    }

    override fun findByCardId(cardId: CardId): Account? = store[cardId]
    override fun findAllByAddress(address: Address): List<Account> = store.filter { it.value.address == address }
        .values.toList()

    override fun clean() {
        store = ConcurrentHashMap()
    }
}
