package com.skydoves.pokedex

import android.app.Application
import com.skydoves.pokedex.upgrader.di.appComponent
import com.skydoves.pokedex.utils.TypesInit
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@HiltAndroidApp
class PokedexApp : Application() {
  override fun onCreate() {
    super.onCreate()
    configureDI()
  }


  private fun configureDI() = startKoin {
    androidContext(this@PokedexApp)
    modules(appComponent)
  }
}
