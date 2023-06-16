package com.dddeurope.recycle.events

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.time.LocalDateTime

data class EventMessage(
    @JsonProperty("event_id")
    val eventId: String,

    @JsonProperty("payload")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type")
    @JsonSubTypes(
        JsonSubTypes.Type(value = IdCardRegistered::class, name = "IdCardRegistered"),
        JsonSubTypes.Type(value = ExemptionWasGranted::class, name = "ExemptionWasGranted"),
        JsonSubTypes.Type(value = DiscountWasBought::class, name = "DiscountWasBought"),
        JsonSubTypes.Type(value = IdCardScannedAtEntranceGate::class, name = "IdCardScannedAtEntranceGate"),
        JsonSubTypes.Type(value = WeightWasMeasured::class, name = "WeightWasMeasured"),
        JsonSubTypes.Type(value = FractionWasSelected::class, name = "FractionWasSelected"),
        JsonSubTypes.Type(value = FractionWasDropped::class, name = "FractionWasDropped"),
        JsonSubTypes.Type(value = IdCardScannedAtExitGate::class, name = "IdCardScannedAtExitGate"),
        JsonSubTypes.Type(value = PriceWasCalculated::class, name = "PriceWasCalculated")
    )
    val payload: Event,

    @JsonProperty("created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @JsonProperty("type")
    val type: String = payload::class.simpleName!!
)