

package me.sjihh.pokedex.core.repository

import androidx.annotation.WorkerThread
import me.sjihh.pokedex.core.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface MainRepository {

  @WorkerThread
  fun fetchPokemonList(
    page: Int,
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit,
  ): Flow<List<Pokemon>>
}
