package com.skydoves.pokedex.type.element.model

data class TypeEffectiveness(val type: Type, val multiplier: Number) {
    operator fun plus(other: TypeEffectiveness): TypeEffectiveness {
        val newMultiplier = multiplier.toDouble() + other.multiplier.toDouble()
        return TypeEffectiveness(type, multiplier = newMultiplier)
    }

    operator fun minus(other: TypeEffectiveness): TypeEffectiveness {
        val newMultiplier = multiplier.toDouble() - other.multiplier.toDouble()
        return TypeEffectiveness(type, multiplier = newMultiplier)
    }
}