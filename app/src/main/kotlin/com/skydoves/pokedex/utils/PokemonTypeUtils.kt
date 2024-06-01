package com.skydoves.pokedex.utils

import com.skydoves.pokedex.R

/**
 * Utility class for Pokemon types.
 * Provides methods to get the color resource ID for a given Pokemon type.
 */
object PokemonTypeUtils {

  /**
   * Gets the color resource ID for a given Pokemon type.
   *
   * @param type The Pokemon type.
   * @return The color resource ID corresponding to the Pokemon type.
   *
   * Note: This function is part of the Pokedex and view poke detail functionality.
   * Coder: Nguyen Dang Khoa (ID: 21110045)
   */
  fun getTypeColor(type: String): Int {
    return when (type) {
      "fighting" -> R.color.fighting
      "flying" -> R.color.flying
      "poison" -> R.color.poison
      "ground" -> R.color.ground
      "rock" -> R.color.rock
      "bug" -> R.color.bug
      "ghost" -> R.color.ghost
      "steel" -> R.color.steel
      "fire" -> R.color.fire
      "water" -> R.color.water
      "grass" -> R.color.grass
      "electric" -> R.color.electric
      "psychic" -> R.color.psychic
      "ice" -> R.color.ice
      "dragon" -> R.color.dragon
      "fairy" -> R.color.fairy
      "dark" -> R.color.dark
      else -> R.color.gray_21
    }
  }
}
