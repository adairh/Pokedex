package com.skydoves.pokedex.upgrader.di

import com.skydoves.pokedex.upgrader.ui.dashboard.DashboardViewModel
import com.skydoves.pokedex.upgrader.ui.generation.GenerationViewModel
import com.skydoves.pokedex.upgrader.ui.home.HomeViewModel
import com.skydoves.pokedex.upgrader.ui.pokedex.PokedexViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { DashboardViewModel(get()) }
    viewModel { GenerationViewModel() }
    viewModel { HomeViewModel() }
    viewModel { PokedexViewModel(get(), get()) }
}
