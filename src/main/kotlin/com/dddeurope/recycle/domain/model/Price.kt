package com.dddeurope.recycle.domain.model

@JvmInline
value class Price(val price: Double) {

    fun plus(price: Price): Price = Price(this.price + price.price)
}
