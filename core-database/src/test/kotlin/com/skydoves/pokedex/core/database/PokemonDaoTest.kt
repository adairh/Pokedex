

package me.sjihh.pokedex.core.database

import me.sjihh.pokedex.core.database.entitiy.mapper.asEntity
import me.sjihh.pokedex.core.test.MockUtil.mockPokemon
import me.sjihh.pokedex.core.test.MockUtil.mockPokemonList
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
class PokemonDaoTest : LocalDatabase() {

  private lateinit var pokemonDao: PokemonDao

  @Before
  fun init() {
    pokemonDao = db.pokemonDao()
  }

  @Test
  fun insertAndLoadPokemonListTest() = runBlocking {
    val mockDataList = mockPokemonList().asEntity()
    pokemonDao.insertPokemonList(mockDataList)

    val loadFromDB = pokemonDao.getPokemonList(page_ = 0)
    assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

    val mockData = listOf(mockPokemon()).asEntity()[0]
    assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))
  }
}
