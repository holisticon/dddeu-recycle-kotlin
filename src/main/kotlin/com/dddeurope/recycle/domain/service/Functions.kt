package com.dddeurope.recycle.domain.service

import com.dddeurope.recycle.domain.model.*

fun calculateFractionPrice(weight: Weight, rate: Rate): Price = Price(weight.weight * rate.rate)