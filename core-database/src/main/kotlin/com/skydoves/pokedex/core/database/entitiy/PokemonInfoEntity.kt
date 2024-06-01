
package me.sjihh.pokedex.core.database.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.sjihh.pokedex.core.model.PokemonInfo

@Entity
data class PokemonInfoEntity(
  @PrimaryKey val id: Int,
  val name: String,
  val height: Int,
  val weight: Int,
  val experience: Int,
  val types: List<PokemonInfo.TypeResponse>,
  val hp: Int,
  val attack: Int,
  val defense: Int,
  val speed: Int,
  val exp: Int,
)
