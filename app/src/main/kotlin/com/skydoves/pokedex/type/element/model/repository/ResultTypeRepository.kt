package com.skydoves.pokedex.type.element.model.repository

import com.skydoves.pokedex.type.element.model.datasource.ResultTypeDataSource
import javax.inject.Inject

class ResultTypeRepository @Inject constructor(
    currentTypesDataSource: ResultTypeDataSource
) {
    val resultTypeFlow = currentTypesDataSource.retrieveResultType()
}