

package me.sjihh.pokedex.core.repository

import androidx.annotation.WorkerThread
import me.sjihh.pokedex.core.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

interface DetailRepository {

  @WorkerThread
  fun fetchPokemonInfo(
    name: String,
    onComplete: () -> Unit,
    onError: (String?) -> Unit,
  ): Flow<PokemonInfo>
}
