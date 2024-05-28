package com.skydoves.pokedex.type.element.model.datasource

import com.skydoves.pokedex.type.element.model.Type
import kotlinx.coroutines.flow.Flow

interface TypesDataSource {
    fun retrieveTypes(): Flow<List<Type>>
}