package com.skydoves.pokedex

import com.skydoves.pokedex.type.ElementData

data class TypeEffectiveness(val type: ElementData, val multiplier: Number) {
    operator fun plus(other: TypeEffectiveness): TypeEffectiveness {
        val newMultiplier = multiplier.toDouble() + other.multiplier.toDouble()
        return TypeEffectiveness(type, multiplier = newMultiplier)
    }

    operator fun minus(other: TypeEffectiveness): TypeEffectiveness {
        val newMultiplier = multiplier.toDouble() - other.multiplier.toDouble()
        return TypeEffectiveness(type, multiplier = newMultiplier)
    }
}