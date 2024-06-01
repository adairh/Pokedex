package me.sjihh.pokedex.type

import me.sjihh.pokedex.ResultType
import me.sjihh.pokedex.TypeEffectiveness
import me.sjihh.pokedex.asTypeEffectivenessList

/**
 *
 * @coder Nguyen Dang Khoa and Lam Nguyen Huy Hoang
 */
data class ElementData(
  val iconResId: Int,
  val name: String,
  val backgroundColorResId: Int,
  val strong: List<ElementData> = emptyList(),
  val weak: List<ElementData> = emptyList(),
  val resistant: List<TypeEffectiveness> = emptyList(),
  val vulnerable: List<TypeEffectiveness> = emptyList(),
  val immune: List<TypeEffectiveness> = emptyList(),
): Comparable<ElementData> {
  /**
   * Adds types that this type is strong against.
   * @param types The types to be added.
   * @return A new ElementData instance.
   */
  fun strongAgainst(vararg types: ElementData): ElementData {
    val strong = types.asList() + strong
    return ElementData(iconResId, name, backgroundColorResId, strong, weak, resistant, vulnerable, immune)
  }

  /**
   * Adds types that this type is weak against.
   * @param types The types to be added.
   * @return A new ElementData instance.
   */
  fun weakAgainst(vararg types: ElementData): ElementData {
    val weak = types.asList() + weak
    return ElementData(iconResId, name, backgroundColorResId, strong, weak, resistant, vulnerable, immune)
  }

  /**
   * Adds types that this type is resistant to.
   * @param types The types to be added.
   * @return A new ElementData instance.
   */
  fun resistantTo(vararg types: ElementData): ElementData {
    val resistant = (types.asTypeEffectivenessList() + resistant).sortedBy { (type) -> type }
    return ElementData(iconResId, name, backgroundColorResId, strong, weak, resistant, vulnerable, immune)
  }

  /**
   * Adds types that this type is vulnerable to.
   * @param types The types to be added.
   * @return A new ElementData instance.
   */
  fun vulnerableTo(vararg types: ElementData): ElementData {
    val vulnerable = (types.asTypeEffectivenessList() + vulnerable).sortedBy { (type) -> type }
    return ElementData(iconResId, name, backgroundColorResId, strong, weak, resistant, vulnerable, immune)
  }

  /**
   * Adds types that this type is immune to.
   * @param types The types to be added.
   * @return A new ElementData instance.
   */
  fun immuneTo(vararg types: ElementData): ElementData {
    val immune = (types.asTypeEffectivenessList(multiplier = 2) + immune)
      .sortedBy { (type) -> type }
    return ElementData(iconResId, name, backgroundColorResId, strong, weak, resistant, vulnerable, immune)
  }

  /**
   * Combines this type with another type and calculates the overall type effectiveness.
   * @param other The other ElementData to be combined with.
   * @return A ResultType representing the combined type effectiveness.
   */
  operator fun plus(other: ElementData): ResultType {
    return ResultType(
      types = listOf(this, other),
      resistant = resistant.newResistantListConsidering(
        vulnerable,
        otherVulnerable = other.vulnerable,
        otherResistant = other.resistant
      ).sortedBy { (type) -> type },
      vulnerable = vulnerable.newVulnerableListConsidering(
        resistant,
        otherVulnerable = other.vulnerable,
        otherResistant = other.resistant
      ).sortedBy { (type) -> type }
    )
  }

  /**
   * Converts this ElementData instance to a ResultType instance.
   * @return A ResultType representing the type effectiveness.
   */
  fun asResultType(): ResultType {
    return ResultType(
      types = listOf(this),
      resistant = resistant,
      vulnerable = vulnerable,
      strong = strong.asTypeEffectivenessList(),
      weak = weak.asTypeEffectivenessList()
    )
  }

  /**
   * Compares this ElementData instance with another ElementData instance based on their names.
   * @param other The other ElementData instance to be compared with.
   * @return An integer representing the comparison result.
   */
  override fun compareTo(other: ElementData): Int {
    return name.compareTo(other.name)
  }
}