package me.sjihh.pokedex

import me.sjihh.pokedex.type.ElementData

/**
 * Represents the effectiveness of a type against another type.
 * Stores the type and its effectiveness multiplier.
 *
 * Note: This data class is part of the Elements of pokemon functionality.
 * Coder: Nguyen Dang Khoa (ID: 21110045)
 */
data class TypeEffectiveness(val type: ElementData, val multiplier: Number) {

  /**
   * Adds the effectiveness of two TypeEffectiveness instances.
   *
   * @param other The TypeEffectiveness instance to be added.
   * @return A new TypeEffectiveness instance with the combined effectiveness.
   *
   * Note: This function is part of the Elements of pokemon functionality.
   * Coder: Nguyen Dang Khoa (ID: 21110045)
   */
  operator fun plus(other: TypeEffectiveness): TypeEffectiveness {
    val newMultiplier = multiplier.toDouble() + other.multiplier.toDouble()
    return TypeEffectiveness(type, multiplier = newMultiplier)
  }

  /**
   * Subtracts the effectiveness of two TypeEffectiveness instances.
   *
   * @param other The TypeEffectiveness instance to be subtracted.
   * @return A new TypeEffectiveness instance with the subtracted effectiveness.
   *
   * Note: This function is part of the Elements of pokemon functionality.
   * Coder: Nguyen Dang Khoa (ID: 21110045)
   */
  operator fun minus(other: TypeEffectiveness): TypeEffectiveness {
    val newMultiplier = multiplier.toDouble() - other.multiplier.toDouble()
    return TypeEffectiveness(type, multiplier = newMultiplier)
  }
}
