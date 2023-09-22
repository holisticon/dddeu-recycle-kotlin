package com.dddeurope.recycle.domain.service

import org.springframework.stereotype.*

@Component
class PriceCalculator(
    private val accountRepository: AccountRepository,
) {

}
