/**
 * Represents a list of [TypeEffectiveness] and provides extension functions for type manipulation.
 */
package com.skydoves.pokedex.type

import com.skydoves.pokedex.TypeEffectiveness

/**
 * Converts a list of [TypeEffectiveness] into a list of [ElementData].
 * This function is used to extract the types from a list of [TypeEffectiveness].
 */
val List<TypeEffectiveness>.types: List<ElementData> get() { return map { (type) -> type } }

/**
 * Reduces the multipliers of types or removes them if they exist in another list.
 * This function is useful for decreasing multipliers or removing types from the current list if they exist in another list.
 * @param other The list of [TypeEffectiveness] to compare against.
 * @return A new list of [TypeEffectiveness] with decreased multipliers or removed types.
 */
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

/**
 * Increases the multipliers of types and removes duplicates.
 * This function is useful for increasing multipliers and removing duplicate types in the current list.
 * @return A new list of [TypeEffectiveness] with increased multipliers and removed duplicates.
 */
fun List<TypeEffectiveness>.increaseMultiplierAndRemoveDuplicates(): List<TypeEffectiveness> {
  return groupBy { (type) -> type }
    .map { (_, typeEffectivenessList) -> typeEffectivenessList
      .reduce { acc, typeEffectiveness ->  acc + typeEffectiveness }
    }
}

/**
 * Filters types that exist in another list of [TypeEffectiveness].
 * This function is used to filter types from the current list that exist in another list.
 * @param other The list of [TypeEffectiveness] to compare against.
 * @return A new list of [TypeEffectiveness] containing types that exist in both lists.
 */
fun List<TypeEffectiveness>.filterTypesThatContainsIn(
  other: List<TypeEffectiveness>
): List<TypeEffectiveness> {
  return filter { (type) -> other.types.contains(type) }
}

/**
 * Creates a new list of resistant types considering vulnerabilities and resistances from other lists.
 * This function is used to generate a new list of resistant types based on vulnerabilities and resistances from other lists.
 * @param vulnerable The list of vulnerable types.
 * @param otherVulnerable The list of vulnerable types from another source.
 * @param otherResistant The list of resistant types from another source.
 * @return A new list of resistant types considering vulnerabilities and resistances.
 * @coder Nguyen Dang Khoa (ID: 21110045)
 */
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

/**
 * Creates a new list of vulnerable types considering resistances and vulnerabilities from other lists.
 * This function is used to generate a new list of vulnerable types based on resistances and vulnerabilities from other lists.
 * @param resistant The list of resistant types.
 * @param otherVulnerable The list of vulnerable types from another source.
 * @param otherResistant The list of resistant types from another source.
 * @return A new list of vulnerable types considering resistances and vulnerabilities.
 * @coder Lam Nguyen Huy Hoang (ID: 21110028)
 */
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
