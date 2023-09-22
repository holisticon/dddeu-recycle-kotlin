package com.dddeurope.recycle.domain.model

import kotlin.math.*

@JvmInline
value class Weight(val weight: Int) {

    fun minus(weight: Weight): Weight = Weight(max(0, this.weight - weight.weight))
}
