package com.skydoves.pokedex.upgrader.di

import com.skydoves.pokedex.upgrader.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { HomeViewModel() }
}
