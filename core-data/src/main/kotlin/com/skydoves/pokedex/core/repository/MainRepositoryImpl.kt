

package me.sjihh.pokedex.core.repository

import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import me.sjihh.pokedex.core.database.PokemonDao
import me.sjihh.pokedex.core.database.entitiy.mapper.asDomain
import me.sjihh.pokedex.core.database.entitiy.mapper.asEntity
import me.sjihh.pokedex.core.model.Pokemon
import me.sjihh.pokedex.core.network.Dispatcher
import me.sjihh.pokedex.core.network.PokedexAppDispatchers
import me.sjihh.pokedex.core.network.service.PokedexClient
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@VisibleForTesting
class MainRepositoryImpl @Inject constructor(
  private val pokedexClient: PokedexClient,
  private val pokemonDao: PokemonDao,
  @Dispatcher(PokedexAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : MainRepository {

  @WorkerThread
  override fun fetchPokemonList(
    page: Int,
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit,
  ) = flow {
    var pokemons = pokemonDao.getPokemonList(page).asDomain()
    if (pokemons.isEmpty()) {
      /**
       * fetches a list of [Pokemon] from the network and getting [ApiResponse] asynchronously.
       * @see [suspendOnSuccess](https://github.com/skydoves/sandwich#apiresponse-extensions-for-coroutines)
       */
      val response = pokedexClient.fetchPokemonList(page = page)
      response.suspendOnSuccess {
        pokemons = data.results
        pokemons.forEach { pokemon -> pokemon.page = page }
        pokemonDao.insertPokemonList(pokemons.asEntity())
        emit(pokemonDao.getAllPokemonList(page).asDomain())
      }.onFailure { // handles the all error cases from the API request fails.
        onError(message())
      }
    } else {
      emit(pokemonDao.getAllPokemonList(page).asDomain())
    }
  }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
