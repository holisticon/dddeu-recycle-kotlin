package com.dddeurope.recycle.infrastructure.command

import com.fasterxml.jackson.annotation.*
import java.util.*


interface Command

data class CalculatePrice(
    @JsonProperty("card_id") val cardId: String
) : Command
