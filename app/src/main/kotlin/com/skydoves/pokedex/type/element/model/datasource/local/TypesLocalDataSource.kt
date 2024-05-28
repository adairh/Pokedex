package com.skydoves.pokedex.type.element.model.datasource.local

import com.skydoves.pokedex.type.element.model.Type
import com.skydoves.pokedex.type.element.model.datasource.TypesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TypesLocalDataSource @Inject constructor(): TypesDataSource {
    private val types: List<Type>

    init {
        val normal = Type(name = "Normal")
        val fighting = Type(name = "Fighting")
        val flying = Type(name = "Flying")
        val poison = Type(name = "Poison")
        val ground = Type(name = "Ground")
        val rock = Type(name = "Rock")
        val bug = Type(name = "Bug")
        val ghost = Type(name = "Ghost")
        val steel = Type(name = "Steel")
        val fire = Type(name = "Fire")
        val water = Type(name = "Water")
        val grass = Type(name = "Grass")
        val electric = Type(name = "Electric")
        val psychic = Type(name = "Psychic")
        val ice = Type(name = "Ice")
        val dragon = Type(name = "Dragon")
        val fairy = Type(name = "Fairy")
        val dark = Type(name = "Dark")

        types = listOf(
            normal
                .strongAgainst()
                .weakAgainst(rock, ghost, steel)
                .vulnerableTo(fighting)
                .immuneTo(ghost),
            fighting
                .strongAgainst(normal, rock, steel, ice, dark, flying, poison)
                .weakAgainst(flying, poison, psychic, bug, ghost, fairy)
                .resistantTo(rock, bug, dark)
                .vulnerableTo(flying, psychic, fairy),
            flying
                .strongAgainst(fighting, bug, grass)
                .weakAgainst(rock, steel, electric)
                .resistantTo(fighting, bug, grass)
                .vulnerableTo(rock, electric, ice)
                .immuneTo(ground),
            poison
                .strongAgainst(grass, fairy)
                .weakAgainst(poison, ground, rock, ghost, steel)
                .resistantTo(fighting, poison, grass, fairy)
                .vulnerableTo(ground, psychic),
            ground
                .strongAgainst(poison, rock, steel, fire, electric)
                .weakAgainst(flying, bug, grass)
                .resistantTo(poison, rock)
                .vulnerableTo(water, grass, ice)
                .immuneTo(electric),
            rock
                .strongAgainst(flying, bug, fire, ice)
                .weakAgainst(fighting, ground, steel)
                .resistantTo(normal, flying, poison, fire)
                .vulnerableTo(fighting, ground, steel, water, grass),
            bug
                .strongAgainst(grass, psychic, dark)
                .weakAgainst(fighting, flying, poison, ghost, steel, fire, fairy)
                .resistantTo(fighting, ground, grass)
                .vulnerableTo(flying, rock, fire),
            ghost
                .strongAgainst(ghost, psychic)
                .weakAgainst(normal, dark)
                .resistantTo(fighting, poison, bug)
                .vulnerableTo(ghost, dark)
                .immuneTo(normal),
            steel
                .strongAgainst(rock, ice, fairy)
                .weakAgainst(steel, fire, water, electric)
                .resistantTo(normal, flying, rock, bug, steel, grass, psychic, ice, dragon, fairy)
                .vulnerableTo(fighting, ground, fire)
                .immuneTo(poison),
            fire
                .strongAgainst(bug, steel, grass, ice)
                .weakAgainst(rock, fire, water, dragon)
                .resistantTo(bug, steel, fire, grass, ice)
                .vulnerableTo(ground, rock, water),
            water
                .strongAgainst(ground, rock, fire)
                .weakAgainst(water, grass, dragon)
                .resistantTo(steel, fire, water, ice)
                .vulnerableTo(grass, electric),
            grass
                .strongAgainst(ground, rock, water)
                .weakAgainst(flying, poison, bug, steel, fire, grass, dragon)
                .resistantTo(ground, water, grass, electric)
                .vulnerableTo(flying, poison, bug, fire, ice),
            electric
                .strongAgainst(flying, water)
                .weakAgainst(ground, grass, electric, dragon)
                .resistantTo(flying, steel, electric)
                .vulnerableTo(ground),
            psychic
                .strongAgainst(fighting, poison)
                .weakAgainst(steel, psychic, dark)
                .resistantTo(fighting, psychic)
                .vulnerableTo(bug, ghost, dark),
            ice
                .strongAgainst(flying, ground, grass, dragon)
                .weakAgainst(steel, fire, water, ice)
                .resistantTo(ice)
                .vulnerableTo(fighting, rock, steel, fire),
            dragon
                .strongAgainst(dragon)
                .weakAgainst(steel, fairy)
                .resistantTo(fire, water, grass, electric)
                .vulnerableTo(ice, dragon, fairy),
            fairy
                .strongAgainst(fighting, dragon, dark)
                .weakAgainst(poison, steel, fire)
                .resistantTo(fighting, bug, dark)
                .vulnerableTo(poison, steel)
                .immuneTo(dragon),
            dark
                .strongAgainst(ghost, psychic)
                .weakAgainst(fighting, dark, fairy)
                .resistantTo(ghost, dark)
                .vulnerableTo(fighting, bug, fairy)
                .immuneTo(psychic),
        )
    }

    override fun retrieveTypes(): Flow<List<Type>> = flow { emit(types) }
}