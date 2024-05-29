package com.skydoves.pokedex

import android.app.Application
import com.skydoves.pokedex.utils.TypesInit
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PokedexApp : Application() {
  override fun onCreate() {
    super.onCreate()
    instance = this
  }

  companion object {
    lateinit var instance: PokedexApp
      private set
  }
}
