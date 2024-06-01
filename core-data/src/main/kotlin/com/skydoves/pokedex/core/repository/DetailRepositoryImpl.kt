

package me.sjihh.pokedex.core.repository

import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import me.sjihh.pokedex.core.database.PokemonInfoDao
import me.sjihh.pokedex.core.database.entitiy.mapper.asDomain
import me.sjihh.pokedex.core.database.entitiy.mapper.asEntity
import me.sjihh.pokedex.core.model.PokemonInfo
import me.sjihh.pokedex.core.network.Dispatcher
import me.sjihh.pokedex.core.network.PokedexAppDispatchers
import me.sjihh.pokedex.core.network.model.PokemonErrorResponse
import me.sjihh.pokedex.core.network.model.mapper.ErrorResponseMapper
import me.sjihh.pokedex.core.network.service.PokedexClient
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

@VisibleForTesting
class DetailRepositoryImpl @Inject constructor(
  private val pokedexClient: PokedexClient,
  private val pokemonInfoDao: PokemonInfoDao,
  @Dispatcher(PokedexAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : DetailRepository {

  @WorkerThread
  override fun fetchPokemonInfo(name: String, onComplete: () -> Unit, onError: (String?) -> Unit) =
    flow {
      val pokemonInfo = pokemonInfoDao.getPokemonInfo(name)
      if (pokemonInfo == null) {
        /**
         * fetches a [PokemonInfo] from the network and getting [ApiResponse] asynchronously.
         * @see [suspendOnSuccess](https://github.com/skydoves/sandwich#apiresponse-extensions-for-coroutines)
         */
        val response = pokedexClient.fetchPokemonInfo(name = name)
        response.suspendOnSuccess {
          pokemonInfoDao.insertPokemonInfo(data.asEntity())
          emit(data)
        }
          // handles the case when the API request gets an error response.
          // e.g., internal server error.
          .onError {
            /** maps the [ApiResponse.Failure.Error] to the [PokemonErrorResponse] using the mapper. */
            map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
          }
          // handles the case when the API request gets an exception response.
          // e.g., network connection error.
          .onException { onError(message) }
      } else {
        emit(pokemonInfo.asDomain())
      }
    }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
