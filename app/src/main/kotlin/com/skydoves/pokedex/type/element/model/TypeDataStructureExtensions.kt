package com.skydoves.pokedex.type.element.model

fun Collection<Type>.asResultType(): ResultType {
    return fold(initial = ResultType()) { acc, type -> acc + type }
}

fun Array<out Type>.asTypeEffectivenessList(multiplier: Number = 1): List<TypeEffectiveness> {
    return map { type -> TypeEffectiveness(type, multiplier) }
}

fun Collection<Type>.asTypeEffectivenessList(multiplier: Number = 1): List<TypeEffectiveness> {
    return toTypedArray().asTypeEffectivenessList(multiplier)
}
