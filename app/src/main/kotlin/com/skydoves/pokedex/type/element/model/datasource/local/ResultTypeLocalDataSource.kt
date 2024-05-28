package com.skydoves.pokedex.type.element.model.datasource.local

import com.skydoves.pokedex.type.element.model.ResultType
import com.skydoves.pokedex.type.element.model.datasource.ResultTypeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class ResultTypeLocalDataSource @Inject constructor(): ResultTypeDataSource {
    private var resultType: ResultType = ResultType()
    private val resultTypeStateFlow = MutableStateFlow(resultType)

    override fun retrieveResultType(): Flow<ResultType> {
        return resultTypeStateFlow.filterNotNull()
    }

    override fun updateResultType(resultType: ResultType) {
        this.resultType = resultType
        resultTypeStateFlow.update { resultType }
    }
}