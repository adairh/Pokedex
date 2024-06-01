package me.sjihh.pokedex.news.repository

import me.sjihh.pokedex.news.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET

interface PokemonService {
    @GET("pokemon.json")
    fun get(): Call<List<Pokemon>>
}
