package com.skydoves.pokedex

import com.skydoves.pokedex.type.ElementData

/**
 * Converts a collection of ElementData into a ResultType.
 *
 * @return A ResultType representing the collection of ElementData.
 *
 * Note: This function is part of the Pokedex and view poke detail functionality.
 * Coder: Nguyen Dang Khoa (ID: 21110045)
 */
fun Collection<ElementData>.asResultType(): ResultType {
  return fold(initial = ResultType()) { acc, type -> acc + type }
}

/**
 * Converts an array of ElementData into a list of TypeEffectiveness with the given multiplier.
 *
 * @param multiplier The multiplier to apply to each TypeEffectiveness instance.
 * @return A list of TypeEffectiveness instances representing the array of ElementData.
 *
 * Note: This function is part of the Pokedex and view poke detail functionality.
 * Coder: Nguyen Dang Khoa (ID: 21110045)
 */
fun Array<out ElementData>.asTypeEffectivenessList(multiplier: Number = 1): List<TypeEffectiveness> {
  return map { type -> TypeEffectiveness(type, multiplier) }
}

/**
 * Converts a collection of ElementData into a list of TypeEffectiveness with the given multiplier.
 *
 * @param multiplier The multiplier to apply to each TypeEffectiveness instance.
 * @return A list of TypeEffectiveness instances representing the collection of ElementData.
 *
 * Note: This function is part of the Pokedex and view poke detail functionality.
 * Coder: Nguyen Dang Khoa (ID: 21110045)
 */
fun Collection<ElementData>.asTypeEffectivenessList(multiplier: Number = 1): List<TypeEffectiveness> {
  return toTypedArray().asTypeEffectivenessList(multiplier)
}
