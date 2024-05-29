package com.skydoves.pokedex.upgrader.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.skydoves.pokedex.upgrader.database.dao.PokemonDAO
import com.skydoves.pokedex.upgrader.model.Pokemon

class DashboardViewModel(private val pokemonDAO: PokemonDAO) : ViewModel() {

    fun getPokemonById(id: String?): LiveData<Pokemon> {
        return pokemonDAO.getById(id)
    }

    fun getPokemonEvolutionsByIds(ids: List<String>): LiveData<List<Pokemon>> {
        return pokemonDAO.getEvolutionsByIds(ids)
    }
}
