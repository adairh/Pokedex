package com.skydoves.pokedex.news.utils

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.skydoves.pokedex.R
import java.util.Locale

/**
 * Utility class for determining Pokemon color based on their type.
 * Lam Nguyen Huy Hoang
 */
class PokemonColorUtil(private val context: Context) {

  /**
   * Returns the color associated with the given Pokemon type.
   *
   * @param typeOfPokemon The type of the Pokemon.
   * @return The color associated with the Pokemon type.
   */
  @ColorInt
  fun getPokemonColor(typeOfPokemon: List<String>?): Int {
    val type = typeOfPokemon?.getOrNull(0)
    val color = when (type?.toLowerCase(Locale.ROOT)) {
      "grass", "bug" -> R.color.lightTeal
      "fire" -> R.color.lightRed
      "water", "fighting", "normal" -> R.color.lightBlue
      "electric", "psychic" -> R.color.lightYellow
      "poison", "ghost" -> R.color.lightPurple
      "ground", "rock" -> R.color.lightBrown
      "dark" -> R.color.black
      else -> R.color.lightBlue
    }
    return convertColor(color)
  }

  /**
   * Converts a color resource to an actual color integer.
   *
   * @param color The color resource.
   * @return The corresponding color integer.
   */
  @ColorInt
  fun convertColor(@ColorRes color: Int): Int {
    return ContextCompat.getColor(context, color)
  }
}
