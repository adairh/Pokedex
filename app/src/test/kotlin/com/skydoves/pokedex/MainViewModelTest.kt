

package me.sjihh.pokedex

import app.cash.turbine.test
import me.sjihh.pokedex.core.database.PokemonDao
import me.sjihh.pokedex.core.database.entitiy.mapper.asEntity
import me.sjihh.pokedex.core.network.service.PokedexClient
import me.sjihh.pokedex.core.network.service.PokedexService
import me.sjihh.pokedex.core.repository.MainRepository
import me.sjihh.pokedex.core.repository.MainRepositoryImpl
import me.sjihh.pokedex.core.test.MainCoroutinesRule
import me.sjihh.pokedex.core.test.MockUtil
import me.sjihh.pokedex.ui.main.MainViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class MainViewModelTest {

  private lateinit var viewModel: MainViewModel
  private lateinit var mainRepository: MainRepository
  private val pokedexService: PokedexService = mock()
  private val pokdexClient: PokedexClient = PokedexClient(pokedexService)
  private val pokemonDao: PokemonDao = mock()

  @get:Rule
  val coroutinesRule = MainCoroutinesRule()

  @Before
  fun setup() {
    mainRepository = MainRepositoryImpl(pokdexClient, pokemonDao, coroutinesRule.testDispatcher)
    viewModel = MainViewModel(mainRepository)
  }

  @Test
  fun fetchPokemonListTest() = runTest {
    val mockData = MockUtil.mockPokemonList()
    whenever(pokemonDao.getPokemonList(page_ = 0)).thenReturn(mockData.asEntity())
    whenever(pokemonDao.getAllPokemonList(page_ = 0)).thenReturn(mockData.asEntity())

    mainRepository.fetchPokemonList(
      page = 0,
      onStart = {},
      onComplete = {},
      onError = {},
    ).test(2.toDuration(DurationUnit.SECONDS)) {
      val item = awaitItem()
      Assert.assertEquals(item[0].page, 0)
      Assert.assertEquals(item[0].name, "bulbasaur")
      Assert.assertEquals(item, MockUtil.mockPokemonList())
      awaitComplete()
    }

    viewModel.fetchNextPokemonList()

    verify(pokemonDao, atLeastOnce()).getPokemonList(page_ = 0)
  }
}
