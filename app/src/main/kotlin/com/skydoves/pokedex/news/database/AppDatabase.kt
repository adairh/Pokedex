package com.skydoves.pokedex.news.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.skydoves.pokedex.news.database.dao.PokemonDAO
import com.skydoves.pokedex.news.model.Pokemon
/**
 *
 * Lam Nguyen Huy Hoang
 */
@Database(entities = [Pokemon::class], version = 5, exportSchema = false)
abstract class  AppDatabase : RoomDatabase() {

    abstract fun pokemonDAO(): PokemonDAO
}
