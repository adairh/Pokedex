package com.skydoves.pokedex.type.element.model.repository

import com.skydoves.pokedex.type.element.model.Type
import com.skydoves.pokedex.type.element.model.asResultType
import com.skydoves.pokedex.type.element.model.datasource.ResultTypeDataSource
import com.skydoves.pokedex.type.element.model.datasource.TypesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class TypesRepository @Inject constructor(
  typesDataSource: TypesDataSource,
  private val currentTypesDataSource: ResultTypeDataSource
) {
    private var types: List<Type> = emptyList()
    val typesFlow: Flow<List<Type>> = typesDataSource.retrieveTypes().onEach { types = it }

    fun addTypesToResultTypeTheTypesAt(indices: List<Int>) {
        val types = indices.mapNotNull { index -> types.getOrNull(index) }
        currentTypesDataSource.updateResultType(resultType = types.asResultType())
    }
}