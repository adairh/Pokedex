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