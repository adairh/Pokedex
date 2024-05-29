package com.skydoves.pokedex

import com.skydoves.pokedex.type.ElementData

fun Collection<ElementData>.asResultType(): ResultType {
    return fold(initial = ResultType()) { acc, type -> acc + type }
}

fun Array<out ElementData>.asTypeEffectivenessList(multiplier: Number = 1): List<TypeEffectiveness> {
    return map { type -> TypeEffectiveness(type, multiplier) }
}

fun Collection<ElementData>.asTypeEffectivenessList(multiplier: Number = 1): List<TypeEffectiveness> {
    return toTypedArray().asTypeEffectivenessList(multiplier)
}
