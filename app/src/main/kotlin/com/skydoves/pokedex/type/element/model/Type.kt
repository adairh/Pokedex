package com.skydoves.pokedex.type.element.model

data class Type(
  val name: String,
  val strong: List<Type> = emptyList(),
  val weak: List<Type> = emptyList(),
  val resistant: List<TypeEffectiveness> = emptyList(),
  val vulnerable: List<TypeEffectiveness> = emptyList(),
): Comparable<Type> {
    fun strongAgainst(vararg types: Type): Type {
        val strong = types.asList() + strong
        return Type(name, strong, weak, resistant, vulnerable)
    }

    fun weakAgainst(vararg types: Type): Type {
        val weak = types.asList() + weak
        return Type(name, strong, weak, resistant, vulnerable)
    }

    fun resistantTo(vararg types: Type): Type {
        val resistant = (types.asTypeEffectivenessList() + resistant).sortedBy { (type) -> type }
        return Type(name, strong, weak, resistant, vulnerable)
    }

    fun vulnerableTo(vararg types: Type): Type {
        val vulnerable = (types.asTypeEffectivenessList() + vulnerable).sortedBy { (type) -> type }
        return Type(name, strong, weak, resistant, vulnerable)
    }

    fun immuneTo(vararg types: Type): Type {
        val resistant = (types.asTypeEffectivenessList(multiplier = 2) + resistant)
            .sortedBy { (type) -> type }
        return Type(name, strong, weak, resistant, vulnerable)
    }

    operator fun plus(other: Type): ResultType {
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

    override fun compareTo(other: Type): Int {
        return name.compareTo(other.name)
    }
}