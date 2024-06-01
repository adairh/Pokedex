package me.sjihh.pokedex

import me.sjihh.pokedex.type.ElementData
import me.sjihh.pokedex.type.newResistantListConsidering
import me.sjihh.pokedex.type.newVulnerableListConsidering

/**
 * Represents the result type after calculating type effectiveness.
 * Stores lists of types, resistant types, vulnerable types, weak types, and strong types.
 *
 * @property types The list of types.
 * @property resistant The list of resistant type effectiveness.
 * @property vulnerable The list of vulnerable type effectiveness.
 * @property weak The list of weak type effectiveness.
 * @property strong The list of strong type effectiveness.
 *
 * Note: This data class is part of the Pokedex and view poke detail functionality.
 * Coder: Nguyen Dang Khoa (ID: 21110045)
 */
data class ResultType(
  val types: List<ElementData> = emptyList(),
  val resistant: List<TypeEffectiveness> = emptyList(),
  val vulnerable: List<TypeEffectiveness> = emptyList(),
  val weak: List<TypeEffectiveness> = emptyList(),
  val strong: List<TypeEffectiveness> = emptyList(),
) {
  /**
   * Combines two ResultType instances by adding their types and calculating new resistant and vulnerable lists.
   *
   * @param other The other ResultType instance to be combined.
   * @return A new ResultType instance with combined type lists and updated resistant and vulnerable lists.
   */
  operator fun plus(other: ResultType): ResultType {
    return ResultType(
      types = types + other.types,
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
   * Adds an ElementData instance to this ResultType by converting it to a ResultType and combining them.
   *
   * @param other The ElementData instance to be added.
   * @return A new ResultType instance with the added ElementData instance.
   */
  operator fun plus(other: ElementData): ResultType {
    val otherResultType = other.asResultType()
    return if (types.isEmpty()) otherResultType else this + otherResultType
  }
}
