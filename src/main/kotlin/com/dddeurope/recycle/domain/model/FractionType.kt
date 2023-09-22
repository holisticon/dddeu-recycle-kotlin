package com.dddeurope.recycle.domain.model

enum class FractionType(val type: String) {
    CONSTRUCTION_WASTE("Construction waste"),
    GREEN_WASTE("Green waste"),
    ;

    companion object {
        @JvmStatic
        fun findByType(type: String): FractionType? = FractionType.values().find { it.type == type }
    }
}
