package com.dddeurope.recycle.events

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

interface Event

data class DiscountWasBought(
    @JsonProperty("card_id") val cardId: String,
    @JsonProperty("discount_percentage") val discountPercentage: Float,
    @JsonProperty("fraction_type") val fractionType: String,
    @JsonProperty("weight") val weight: Int,
    @JsonProperty("expiry_date") val expiryDate: String
) : Event

data class ExemptionWasGranted(
    @JsonProperty("card_id") val cardId: String,
    @JsonProperty("fraction_type") val fractionType: String,
    @JsonProperty("weight") val weight: Int,
    @JsonProperty("expiry_date") val expiryDate: String
) : Event


data class FractionWasDropped(
    @JsonProperty("card_id") val cardId: String,
    @JsonProperty("fraction_type") val fractionType: String,
    @JsonProperty("weight") val weight: Int
) : Event

data class FractionWasSelected(
    @JsonProperty("card_id") val cardId: String,
    @JsonProperty("fraction_type") val fractionType: String
) : Event

data class IdCardRegistered(
    @JsonProperty("card_id") val cardId: String,
    @JsonProperty("person_id") val personId: String,
    @JsonProperty("address") val address: String,
    @JsonProperty("city") val city: String
) : Event

data class IdCardScannedAtEntranceGate(
    @JsonProperty("card_id") val cardId: String,
    @JsonProperty("date") val date: String
) : Event

data class IdCardScannedAtExitGate(
    @JsonProperty("card_id") val cardId: String
) : Event

data class PriceWasCalculated(
    @JsonProperty("card_id") val cardId: String,
    @JsonProperty("price_amount") val amount: Double,
    @JsonProperty("price_currency") val currency: String
) : Event

data class WeightWasMeasured(
    @JsonProperty("card_id") val cardId: String,
    @JsonProperty("weight") val weight: Int
) : Event
