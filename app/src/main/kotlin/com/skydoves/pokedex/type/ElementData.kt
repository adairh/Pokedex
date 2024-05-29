package com.skydoves.pokedex.type

import com.skydoves.pokedex.ResultType
import com.skydoves.pokedex.TypeEffectiveness
import com.skydoves.pokedex.asTypeEffectivenessList

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
  fun strongAgainst(vararg types: ElementData): ElementData {
    val strong = types.asList() + strong
    return ElementData(iconResId, name, backgroundColorResId, strong, weak, resistant, vulnerable, immune)
  }

  fun weakAgainst(vararg types: ElementData): ElementData {
    val weak = types.asList() + weak
    return ElementData(iconResId, name, backgroundColorResId, strong, weak, resistant, vulnerable, immune)
  }

  fun resistantTo(vararg types: ElementData): ElementData {
    val resistant = (types.asTypeEffectivenessList() + resistant).sortedBy { (type) -> type }
    return ElementData(iconResId, name, backgroundColorResId, strong, weak, resistant, vulnerable, immune)
  }

  fun vulnerableTo(vararg types: ElementData): ElementData {
    val vulnerable = (types.asTypeEffectivenessList() + vulnerable).sortedBy { (type) -> type }
    return ElementData(iconResId, name, backgroundColorResId, strong, weak, resistant, vulnerable, immune)
  }

  fun immuneTo(vararg types: ElementData): ElementData {
    val immune = (types.asTypeEffectivenessList(multiplier = 2) + immune)
      .sortedBy { (type) -> type }
    return ElementData(iconResId, name, backgroundColorResId, strong, weak, resistant, vulnerable, immune)
  }

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

  fun asResultType(): ResultType {
    return ResultType(
      types = listOf(this),
      resistant = resistant,
      vulnerable = vulnerable,
      strong = strong.asTypeEffectivenessList(),
      weak = weak.asTypeEffectivenessList()
    )
  }

  override fun compareTo(other: ElementData): Int {
    return name.compareTo(other.name)
  }

}