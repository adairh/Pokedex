package com.skydoves.pokedex.ui

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.ContextCompat
import com.skydoves.pokedex.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

/**
 * Creates a rounded drawable with the given color resource ID.
 *
 * @param context The context.
 * @param colorResId The color resource ID.
 * @param isSelected Indicates if the drawable is selected.
 * @return A rounded GradientDrawable.
 *
 * Note: This function is part of the Pokedex and view poke detail functionality.
 * Coder: Nguyen Dang Khoa (ID: 21110045)
 */
fun createRoundedDrawable(context: Context, colorResId: Int, isSelected: Boolean): GradientDrawable {
  val drawable = GradientDrawable()
  drawable.shape = GradientDrawable.RECTANGLE
  drawable.cornerRadius = 50f
  drawable.setColor(ContextCompat.getColor(context, colorResId))
  if (isSelected) {
    drawable.setStroke(8, ContextCompat.getColor(context, android.R.color.holo_green_light))
  }
  return drawable
}

/**
 * Animates the flipping of a view from front to back or vice versa.
 *
 * @param context The context.
 * @param front The front view.
 * @param back The back view.
 * @param showBack Indicates whether to show the back view.
 *
 * Note: This function is part of the Pokedex and view poke detail functionality.
 * Coder: Nguyen Dang Khoa (ID: 21110045)
 */
fun flipView(context: Context, front: View, back: View, showBack: Boolean) {
  val scale = context.resources.displayMetrics.density
  front.cameraDistance = 8000 * scale
  back.cameraDistance = 8000 * scale

  val flipIn = AnimatorInflater.loadAnimator(context, R.animator.flip_in)
  val flipOut = AnimatorInflater.loadAnimator(context, R.animator.flip_out)

  if (showBack) {
    flipOut.setTarget(front)
    flipIn.setTarget(front)
  } else {
    flipOut.setTarget(front)
    flipIn.setTarget(front)
  }

  flipOut.start()
  flipIn.start()
}

/**
 * Retrieves the ID of a Pokemon by its name using the PokeAPI.
 *
 * @param pokemonName The name of the Pokemon.
 * @return The ID of the Pokemon, or null if an error occurs.
 *
 * Note: This function is part of the Pokedex and view poke detail functionality.
 * Coder: Nguyen Dang Khoa (ID: 21110045)
 */
suspend fun getPokemonId(pokemonName: String): Int? = withContext(Dispatchers.IO) {
  try {
    val url = URL("https://pokeapi.co/api/v2/pokemon/$pokemonName")
    val connection = url.openConnection() as HttpURLConnection
    connection.requestMethod = "GET"

    val responseCode = connection.responseCode
    if (responseCode == HttpURLConnection.HTTP_OK) {
      val inputStream = connection.inputStream
      val jsonString = inputStream.bufferedReader().use { it.readText() }
      val jsonObject = JSONObject(jsonString)
      jsonObject.getInt("id")
    } else {
      println("Error: $responseCode")
      null
    }
  } catch (e: Exception) {
    println("Exception: ${e.message}")
    null
  }
}
