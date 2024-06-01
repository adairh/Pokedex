

package me.sjihh.pokedex.core.data

import app.cash.turbine.test
import me.sjihh.pokedex.core.database.PokemonDao
import me.sjihh.pokedex.core.database.entitiy.mapper.asEntity
import me.sjihh.pokedex.core.network.model.PokemonResponse
import me.sjihh.pokedex.core.network.service.PokedexClient
import me.sjihh.pokedex.core.network.service.PokedexService
import me.sjihh.pokedex.core.repository.MainRepositoryImpl
import me.sjihh.pokedex.core.test.MainCoroutinesRule
import me.sjihh.pokedex.core.test.MockUtil.mockPokemonList
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

class MainRepositoryImplTest {

  private lateinit var repository: MainRepositoryImpl
  private lateinit var client: PokedexClient
  private val service: PokedexService = mock()
  private val pokemonDao: PokemonDao = mock()

  @get:Rule
  val coroutinesRule = MainCoroutinesRule()

  @Before
  fun setup() {
    client = PokedexClient(service)
    repository = MainRepositoryImpl(client, pokemonDao, coroutinesRule.testDispatcher)
  }

  @Test
  fun fetchPokemonListFromNetworkTest() = runTest {
    val mockData =
      PokemonResponse(count = 984, next = null, previous = null, results = mockPokemonList())
    whenever(pokemonDao.getPokemonList(page_ = 0)).thenReturn(emptyList())
    whenever(pokemonDao.getAllPokemonList(page_ = 0)).thenReturn(mockData.results.asEntity())
    whenever(service.fetchPokemonList()).thenReturn(
      ApiResponse.responseOf {
        Response.success(
          mockData,
        )
      },
    )

    repository.fetchPokemonList(
      page = 0,
      onStart = {},
      onComplete = {},
      onError = {},
    ).test(2.toDuration(DurationUnit.SECONDS)) {
      val expectItem = awaitItem()[0]
      assertEquals(expectItem.page, 0)
      assertEquals(expectItem.name, "bulbasaur")
      assertEquals(expectItem, mockPokemonList()[0])
      awaitComplete()
    }

    verify(pokemonDao, atLeastOnce()).getPokemonList(page_ = 0)
    verify(service, atLeastOnce()).fetchPokemonList()
    verify(pokemonDao, atLeastOnce()).insertPokemonList(mockData.results.asEntity())
    verifyNoMoreInteractions(service)
  }

  @Test
  fun fetchPokemonListFromDatabaseTest() = runTest {
    val mockData =
      PokemonResponse(count = 984, next = null, previous = null, results = mockPokemonList())
    whenever(pokemonDao.getPokemonList(page_ = 0)).thenReturn(mockData.results.asEntity())
    whenever(pokemonDao.getAllPokemonList(page_ = 0)).thenReturn(mockData.results.asEntity())

    repository.fetchPokemonList(
      page = 0,
      onStart = {},
      onComplete = {},
      onError = {},
    ).test(2.toDuration(DurationUnit.SECONDS)) {
      val expectItem = awaitItem()[0]
      assertEquals(expectItem.page, 0)
      assertEquals(expectItem.name, "bulbasaur")
      assertEquals(expectItem, mockPokemonList()[0])
      awaitComplete()
    }

    verify(pokemonDao, atLeastOnce()).getPokemonList(page_ = 0)
    verify(pokemonDao, atLeastOnce()).getAllPokemonList(page_ = 0)
  }
}
