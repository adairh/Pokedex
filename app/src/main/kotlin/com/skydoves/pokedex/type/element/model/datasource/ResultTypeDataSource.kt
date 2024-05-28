package com.skydoves.pokedex.type.element.model.datasource

import com.skydoves.pokedex.type.element.model.ResultType
import kotlinx.coroutines.flow.Flow

interface ResultTypeDataSource {
    fun retrieveResultType(): Flow<ResultType>
    fun updateResultType(resultType: ResultType)
}