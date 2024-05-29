package com.skydoves.pokedex.upgrader.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.skydoves.pokedex.upgrader.database.dao.PokemonDAO
import com.skydoves.pokedex.upgrader.model.Pokemon

@Database(entities = [Pokemon::class], version = 5, exportSchema = false)
abstract class  AppDatabase : RoomDatabase() {

    abstract fun pokemonDAO(): PokemonDAO
}
