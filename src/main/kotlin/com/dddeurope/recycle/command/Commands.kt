package com.dddeurope.recycle.command

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*


interface Command

data class CalculatePrice(
    @JsonProperty("card_id") val cardId: String
) : Command
