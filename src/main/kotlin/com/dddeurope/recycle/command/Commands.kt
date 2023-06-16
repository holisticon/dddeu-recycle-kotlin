package com.dddeurope.recycle.command

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*


interface Command

data class CalculatePrice(@field:JsonProperty("card_id") @param:JsonProperty("card_id") val cardId: String) : Command
