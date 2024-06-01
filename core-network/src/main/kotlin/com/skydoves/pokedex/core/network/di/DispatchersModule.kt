

package me.sjihh.pokedex.core.network.di

import me.sjihh.pokedex.core.network.Dispatcher
import me.sjihh.pokedex.core.network.PokedexAppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
internal object DispatchersModule {

  @Provides
  @Dispatcher(PokedexAppDispatchers.IO)
  fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}
