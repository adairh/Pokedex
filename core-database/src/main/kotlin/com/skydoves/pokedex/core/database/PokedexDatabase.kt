
package me.sjihh.pokedex.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.sjihh.pokedex.core.database.entitiy.PokemonEntity
import me.sjihh.pokedex.core.database.entitiy.PokemonInfoEntity

@Database(
  entities = [PokemonEntity::class, PokemonInfoEntity::class],
  version = 2,
  exportSchema = true,
)
@TypeConverters(value = [TypeResponseConverter::class])
abstract class PokedexDatabase : RoomDatabase() {

  abstract fun pokemonDao(): PokemonDao
  abstract fun pokemonInfoDao(): PokemonInfoDao
}
