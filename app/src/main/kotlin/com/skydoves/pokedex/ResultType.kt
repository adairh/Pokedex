package com.skydoves.pokedex

import com.skydoves.pokedex.type.ElementData
import com.skydoves.pokedex.type.newResistantListConsidering
import com.skydoves.pokedex.type.newVulnerableListConsidering

data class ResultType(
  val types: List<ElementData> = emptyList(),
  val resistant: List<TypeEffectiveness> = emptyList(),
  val vulnerable: List<TypeEffectiveness> = emptyList(),
  val weak: List<TypeEffectiveness> = emptyList(),
  val strong: List<TypeEffectiveness> = emptyList(),
) {
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

    operator fun plus(other: ElementData): ResultType {
        val otherResultType = other.asResultType()
        return if (types.isEmpty()) otherResultType else this + otherResultType
    }
}
