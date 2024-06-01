
package me.sjihh.pokedex.core.di

import me.sjihh.pokedex.core.repository.DetailRepository
import me.sjihh.pokedex.core.repository.DetailRepositoryImpl
import me.sjihh.pokedex.core.repository.MainRepository
import me.sjihh.pokedex.core.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

  @Binds
  fun bindsMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

  @Binds
  fun bindsDetailRepository(detailRepositoryImpl: DetailRepositoryImpl): DetailRepository
}
