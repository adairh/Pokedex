

package me.sjihh.pokedex.core.network

import me.sjihh.pokedex.core.network.service.PokedexService
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class PokedexServiceTest : ApiAbstract<PokedexService>() {

  private lateinit var service: PokedexService

  @Before
  fun initService() {
    service = createService(PokedexService::class.java)
  }

  @Throws(IOException::class)
  @Test
  fun fetchPokemonListFromNetworkTest() = runTest {
    enqueueResponse("/PokemonResponse.json")
    val response = service.fetchPokemonList()
    val responseBody = requireNotNull((response as ApiResponse.Success).data)

    assertThat(responseBody.count, `is`(964))
    assertThat(responseBody.results[0].name, `is`("bulbasaur"))
    assertThat(responseBody.results[0].url, `is`("https://pokeapi.co/api/v2/pokemon/1/"))
  }

  @Throws(IOException::class)
  @Test
  fun fetchPokemonInfoFromNetworkTest() = runTest {
    enqueueResponse("/Bulbasaur.json")
    val response = service.fetchPokemonInfo("bulbasaur")
    val responseBody = requireNotNull((response as ApiResponse.Success).data)

    assertThat(responseBody.id, `is`(1))
    assertThat(responseBody.name, `is`("bulbasaur"))
    assertThat(responseBody.height, `is`(7))
    assertThat(responseBody.weight, `is`(69))
    assertThat(responseBody.experience, `is`(64))
  }
}
