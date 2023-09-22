package com.dddeurope.recycle.domain.model

enum class City(val city: String) {
    SOUTH_PARK("South Park") {
        override fun getFractionRate(fractionType: FractionType): Rate =
            when (fractionType) {
                FractionType.CONSTRUCTION_WASTE -> Rate(0.18)
                FractionType.GREEN_WASTE -> Rate(0.12)
            }

        override fun getExemptionFor(fractionType: FractionType): Weight =
            when (fractionType) {
                FractionType.CONSTRUCTION_WASTE -> Weight(100)
                FractionType.GREEN_WASTE -> Weight(50)
            }
    },
    DEFAULT("default") {
        override fun getFractionRate(fractionType: FractionType): Rate =
            when (fractionType) {
                FractionType.CONSTRUCTION_WASTE -> Rate(0.15)
                FractionType.GREEN_WASTE -> Rate(0.09)
            }

        override fun getExemptionFor(fractionType: FractionType): Weight = Weight(0)
    }, ;

    abstract fun getFractionRate(fractionType: FractionType): Rate

    abstract fun getExemptionFor(fractionType: FractionType): Weight

    companion object {
        @JvmStatic
        fun cityForCity(city: String): City =
            City.values().find { it.city == city } ?: DEFAULT
    }
}
