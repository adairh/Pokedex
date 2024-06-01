package me.sjihh.pokedex.news.di

import androidx.room.Room
import me.sjihh.pokedex.R
import me.sjihh.pokedex.news.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
/**
 *
 * Lam Nguyen Huy Hoang
 */
val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            androidApplication().baseContext.getString(R.string.app_name)
        ).build()
    }

    single {
        get<AppDatabase>().pokemonDAO()
    }
}
