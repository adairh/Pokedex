package com.skydoves.pokedex.news.di

import com.skydoves.pokedex.news.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { HomeViewModel() }
}
