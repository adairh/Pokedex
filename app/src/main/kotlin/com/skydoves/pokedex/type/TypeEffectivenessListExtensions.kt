package com.skydoves.pokedex.type

import com.skydoves.pokedex.TypeEffectiveness

val List<TypeEffectiveness>.types: List<ElementData> get() { return map { (type) -> type } }

fun List<TypeEffectiveness>.decreaseMultiplierOrRemoveIfContainsIn(
    other: List<TypeEffectiveness>
): List<TypeEffectiveness> {
    return (this + other)
        .groupBy { (type) -> type }
        .mapNotNull { (_, typeEffectivenessList) ->
            typeEffectivenessList
                .reduce { acc, typeEffectiveness -> acc - typeEffectiveness }
                .takeIf { it.multiplier.toInt() > 0 }
        }
}

fun List<TypeEffectiveness>.increaseMultiplierAndRemoveDuplicates(): List<TypeEffectiveness> {
    return groupBy { (type) -> type }
        .map { (_, typeEffectivenessList) -> typeEffectivenessList
            .reduce { acc, typeEffectiveness ->  acc + typeEffectiveness }
        }
}

fun List<TypeEffectiveness>.filterTypesThatContainsIn(
    other: List<TypeEffectiveness>
): List<TypeEffectiveness> {
    return filter { (type) -> other.types.contains(type) }
}

fun List<TypeEffectiveness>.newResistantListConsidering(
  vulnerable: List<TypeEffectiveness>,
  otherVulnerable: List<TypeEffectiveness>,
  otherResistant: List<TypeEffectiveness>
): List<TypeEffectiveness> {
    val vulnerableThatContainsInResistant = otherVulnerable.filterTypesThatContainsIn(other = this)
    val resistantThatContainsInVulnerable = otherResistant.filterTypesThatContainsIn(vulnerable)

    return (
        decreaseMultiplierOrRemoveIfContainsIn(vulnerableThatContainsInResistant) +
        otherResistant.decreaseMultiplierOrRemoveIfContainsIn(resistantThatContainsInVulnerable)
    ).increaseMultiplierAndRemoveDuplicates()
}

fun List<TypeEffectiveness>.newVulnerableListConsidering(
  resistant: List<TypeEffectiveness>,
  otherVulnerable: List<TypeEffectiveness>,
  otherResistant: List<TypeEffectiveness>
): List<TypeEffectiveness> {
    val resistantThatContainsInVulnerable = otherResistant.filterTypesThatContainsIn(other = this)
    val vulnerableThatContainsInResistant = otherVulnerable.filterTypesThatContainsIn(resistant)

    return (
        decreaseMultiplierOrRemoveIfContainsIn(resistantThatContainsInVulnerable) +
        otherVulnerable.decreaseMultiplierOrRemoveIfContainsIn(vulnerableThatContainsInResistant)
    ).increaseMultiplierAndRemoveDuplicates()
}