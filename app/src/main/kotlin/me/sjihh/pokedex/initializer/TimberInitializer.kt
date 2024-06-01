package me.sjihh.pokedex.initializer

import android.content.Context
import androidx.startup.Initializer
import me.sjihh.pokedex.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {

  /**
   * Initializes Timber logging library in debug mode.
   * If the build is in debug mode, plants a DebugTree for logging.
   *
   * Note: This function is part of the general initialization functionality.
   * Coder: Lam Nguyen Huy Hoang (ID: 21110028)
   */
  override fun create(context: Context) {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
      Timber.d("TimberInitializer is initialized.")
    }
  }

  /**
   * Specifies that this initializer has no dependencies on other initializers.
   *
   * Note: This function is part of the general initialization functionality.
   * Coder: Lam Nguyen Huy Hoang (ID: 21110028)
   */
  override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
