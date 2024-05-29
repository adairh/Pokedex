package com.skydoves.pokedex.type.element.model.datasource

import com.skydoves.pokedex.type.ElementData
import kotlinx.coroutines.flow.Flow

interface TypesDataSource {
    fun retrieveTypes(): Flow<List<ElementData>>
}