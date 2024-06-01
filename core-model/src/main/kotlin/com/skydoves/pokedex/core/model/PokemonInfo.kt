

package me.sjihh.pokedex.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.random.Random

@JsonClass(generateAdapter = true)
data class PokemonInfo(
  @field:Json(name = "id")
  val id: Int,
  @field:Json(name = "name") val name: String,
  @field:Json(name = "height") val height: Int,
  @field:Json(name = "weight") val weight: Int,
  @field:Json(name = "base_experience") val experience: Int,
  @field:Json(name = "types") val types: List<TypeResponse>,
  val hp: Int = Random.nextInt(MAX_HP),
  val attack: Int = Random.nextInt(MAX_ATTACK),
  val defense: Int = Random.nextInt(MAX_DEFENSE),
  val speed: Int = Random.nextInt(MAX_SPEED),
  val exp: Int = Random.nextInt(MAX_EXP),
) {

  fun getIdString(): String = String.format("#%03d", id)
  fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
  fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
  fun getHpString(): String = " $hp/$MAX_HP"
  fun getAttackString(): String = " $attack/$MAX_ATTACK"
  fun getDefenseString(): String = " $defense/$MAX_DEFENSE"
  fun getSpeedString(): String = " $speed/$MAX_SPEED"
  fun getExpString(): String = " $exp/$MAX_EXP"

  @JsonClass(generateAdapter = true)
  data class TypeResponse(
    @field:Json(name = "slot") val slot: Int,
    @field:Json(name = "type") val type: Type,
  )

  @JsonClass(generateAdapter = true)
  data class Type(
    @field:Json(name = "name") val name: String,
  )

  companion object {
    const val MAX_HP = 300
    const val MAX_ATTACK = 300
    const val MAX_DEFENSE = 300
    const val MAX_SPEED = 300
    const val MAX_EXP = 1000
  }
}
