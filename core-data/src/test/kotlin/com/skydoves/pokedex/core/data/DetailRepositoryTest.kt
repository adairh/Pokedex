
package me.sjihh.pokedex.core.data

import app.cash.turbine.test
import me.sjihh.pokedex.core.database.PokemonInfoDao
import me.sjihh.pokedex.core.database.entitiy.mapper.asEntity
import me.sjihh.pokedex.core.network.service.PokedexClient
import me.sjihh.pokedex.core.network.service.PokedexService
import me.sjihh.pokedex.core.repository.DetailRepositoryImpl
import me.sjihh.pokedex.core.test.MainCoroutinesRule
import me.sjihh.pokedex.core.test.MockUtil.mockPokemonInfo
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.responseOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import retrofit2.Response
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class DetailRepositoryTest {

  private lateinit var repository: DetailRepositoryImpl
  private lateinit var client: PokedexClient
  private val service: PokedexService = mock()
  private val pokemonInfoDao: PokemonInfoDao = mock()

  @get:Rule
  val coroutinesRule = MainCoroutinesRule()

  @Before
  fun setup() {
    client = PokedexClient(service)
    repository = DetailRepositoryImpl(client, pokemonInfoDao, coroutinesRule.testDispatcher)
  }

  @Test
  fun fetchPokemonInfoFromNetworkTest() = runTest {
    val mockData = mockPokemonInfo()
    whenever(pokemonInfoDao.getPokemonInfo(name_ = "bulbasaur")).thenReturn(null)
    whenever(service.fetchPokemonInfo(name = "bulbasaur")).thenReturn(
      ApiResponse.responseOf {
        Response.success(
          mockData,
        )
      },
    )

    repository.fetchPokemonInfo(name = "bulbasaur", onComplete = {}, onError = {}).test {
      val expectItem = awaitItem()
      assertEquals(expectItem.id, mockData.id)
      assertEquals(expectItem.name, mockData.name)
      assertEquals(expectItem, mockData)
      awaitComplete()
    }

    verify(pokemonInfoDao, atLeastOnce()).getPokemonInfo(name_ = "bulbasaur")
    verify(service, atLeastOnce()).fetchPokemonInfo(name = "bulbasaur")
    verify(pokemonInfoDao, atLeastOnce()).insertPokemonInfo(mockData.asEntity())
    verifyNoMoreInteractions(service)
  }

  @Test
  fun fetchPokemonInfoFromDatabaseTest() = runTest {
    val mockData = mockPokemonInfo()
    whenever(pokemonInfoDao.getPokemonInfo(name_ = "bulbasaur")).thenReturn(mockData.asEntity())
    whenever(service.fetchPokemonInfo(name = "bulbasaur")).thenReturn(
      ApiResponse.responseOf {
        Response.success(
          mockData,
        )
      },
    )

    repository.fetchPokemonInfo(
      name = "bulbasaur",
      onComplete = {},
      onError = {},
    ).test(5.toDuration(DurationUnit.SECONDS)) {
      val expectItem = awaitItem()
      assertEquals(expectItem.id, mockData.id)
      assertEquals(expectItem.name, mockData.name)
      assertEquals(expectItem, mockData)
      awaitComplete()
    }

    verify(pokemonInfoDao, atLeastOnce()).getPokemonInfo(name_ = "bulbasaur")
    verifyNoMoreInteractions(service)
  }
}
