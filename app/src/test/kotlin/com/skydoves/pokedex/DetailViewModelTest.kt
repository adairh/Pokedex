

package me.sjihh.pokedex

import app.cash.turbine.test
import me.sjihh.pokedex.core.database.PokemonInfoDao
import me.sjihh.pokedex.core.database.entitiy.mapper.asEntity
import me.sjihh.pokedex.core.network.service.PokedexClient
import me.sjihh.pokedex.core.network.service.PokedexService
import me.sjihh.pokedex.core.repository.DetailRepository
import me.sjihh.pokedex.core.repository.DetailRepositoryImpl
import me.sjihh.pokedex.core.test.MainCoroutinesRule
import me.sjihh.pokedex.core.test.MockUtil
import me.sjihh.pokedex.ui.details.DetailViewModel
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

class DetailViewModelTest {

  private lateinit var viewModel: DetailViewModel
  private lateinit var detailRepository: DetailRepository
  private val pokedexService: PokedexService = mock()
  private val pokedexClient: PokedexClient = PokedexClient(pokedexService)
  private val pokemonInfoDao: PokemonInfoDao = mock()

  @get:Rule
  val coroutinesRule = MainCoroutinesRule()

  @Before
  fun setup() {
    detailRepository =
      DetailRepositoryImpl(pokedexClient, pokemonInfoDao, coroutinesRule.testDispatcher)
    viewModel = DetailViewModel(detailRepository, "bulbasaur")
  }

  @Test
  fun fetchPokemonInfoTest() = runTest {
    val mockData = MockUtil.mockPokemonInfo()
    whenever(pokemonInfoDao.getPokemonInfo(name_ = "bulbasaur")).thenReturn(mockData.asEntity())

    detailRepository.fetchPokemonInfo(
      name = "bulbasaur",
      onComplete = { },
      onError = { },
    ).test(2.toDuration(DurationUnit.SECONDS)) {
      val item = awaitItem()
      Assert.assertEquals(item.id, mockData.id)
      Assert.assertEquals(item.name, mockData.name)
      Assert.assertEquals(item, mockData)
      awaitComplete()
    }

    verify(pokemonInfoDao, atLeastOnce()).getPokemonInfo(name_ = "bulbasaur")
  }
}
