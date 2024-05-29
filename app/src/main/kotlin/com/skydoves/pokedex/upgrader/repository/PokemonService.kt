package com.skydoves.pokedex.upgrader.repository

import com.skydoves.pokedex.upgrader.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET

interface PokemonService {
    @GET("pokemon.json")
    fun get(): Call<List<Pokemon>>
}
