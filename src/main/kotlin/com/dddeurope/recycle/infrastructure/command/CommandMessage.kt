package com.dddeurope.recycle.infrastructure.command

import com.fasterxml.jackson.annotation.*

data class CommandMessage(
    @JsonProperty("command_id")
    var commandId: String? = null,

    @JsonProperty("created_at")
    var createdAt: String? = null,

    @JsonProperty("type")
    var type: String? = null,

    @JsonProperty("payload")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type")
    @JsonSubTypes(JsonSubTypes.Type(value = CalculatePrice::class, name = "CalculatePrice"))
    var payload: Command? = null

)