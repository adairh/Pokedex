package me.sjihh.pokedex.utils

import me.sjihh.pokedex.R
import me.sjihh.pokedex.type.ElementData

class TypesInit {
  companion object {
    @JvmStatic
    var types: List<ElementData> = emptyList()

    init {
      setinit()
    }

    @JvmStatic
    private fun setinit() {
      val normal   =  ElementData(R.drawable.ic_type_normal, "Normal", R.color.gray_21)
      val fighting =  ElementData(R.drawable.ic_type_fighting, "Fighting", R.color.fighting)
      val flying   =  ElementData(R.drawable.ic_type_flying, "Flying", R.color.flying)
      val poison   =  ElementData(R.drawable.ic_type_poison, "Poison", R.color.poison)
      val ground   =  ElementData(R.drawable.ic_type_ground, "Ground", R.color.ground)
      val rock     =  ElementData(R.drawable.ic_type_rock, "Rock", R.color.rock)
      val bug      =  ElementData(R.drawable.ic_type_bug, "Bug", R.color.bug)
      val ghost    =  ElementData(R.drawable.ic_type_ghost, "Ghost", R.color.ghost)
      val steel    =  ElementData(R.drawable.ic_type_steel, "Steel", R.color.steel)
      val fire     =  ElementData(R.drawable.ic_type_fire, "Fire", R.color.fire)
      val water    =  ElementData(R.drawable.ic_type_water, "Water", R.color.water)
      val grass    =  ElementData(R.drawable.ic_type_grass, "Grass", R.color.grass)
      val electric =  ElementData(R.drawable.ic_type_electric, "Electric", R.color.electric)
      val psychic  =  ElementData(R.drawable.ic_type_psychic, "Psychic", R.color.psychic)
      val ice      =  ElementData(R.drawable.ic_type_ice, "Ice", R.color.ice)
      val dragon   =  ElementData(R.drawable.ic_type_dragon, "Dragon", R.color.dragon)
      val dark     =  ElementData(R.drawable.ic_type_dark, "Dark", R.color.dark)
      val fairy    =  ElementData(R.drawable.ic_type_fairy, "Fairy", R.color.fairy)


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
  }

}