package com.skydoves.pokedex.upgrader.ui.pokedex

import androidx.lifecycle.ViewModel
import com.skydoves.pokedex.upgrader.database.dao.PokemonDAO
import com.skydoves.pokedex.upgrader.model.Pokemon
import com.skydoves.pokedex.upgrader.repository.PokemonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class PokedexViewModel(
    private val pokemonDAO: PokemonDAO,
    private val pokemonService: PokemonService
) : ViewModel() {

    init {
        initNetworkRequest()
    }

    private fun initNetworkRequest() {
        /*val call = pokemonService.get()

        call.enqueue(object : Callback<List<Pokemon>?> {
            override fun onResponse(
                call: Call<List<Pokemon>?>?,
                response: Response<List<Pokemon>?>?
            ) {
                response?.body()?.let { pokemons: List<Pokemon> ->
                    thread {
                        pokemonDAO.add(pokemons)
                    }
                }
            }

            override fun onFailure(call: Call<List<Pokemon>?>?, t: Throwable?) {
                // TODO handle failure
            }
        })*/
    }

    fun getListPokemon() = pokemonDAO.all()
}
