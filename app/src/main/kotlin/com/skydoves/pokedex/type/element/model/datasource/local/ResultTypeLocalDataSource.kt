package com.skydoves.pokedex.type.element.model.datasource.local

import com.skydoves.pokedex.ResultType
import com.skydoves.pokedex.type.element.model.datasource.ResultTypeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Local data source implementation for managing the result type within the app.
 */
class ResultTypeLocalDataSource @Inject constructor() : ResultTypeDataSource {
  private var resultType: ResultType = ResultType()
  private val resultTypeStateFlow = MutableStateFlow(resultType)

  /**
   * Retrieves the current result type as a flow.
   *
   * @return A flow emitting the current result type.
   */
  override fun retrieveResultType(): Flow<ResultType> {
    return resultTypeStateFlow.filterNotNull()
  }

  /**
   * Updates the result type with the provided value.
   *
   * @param resultType The new result type to be updated.
   */
  override fun updateResultType(resultType: ResultType) {
    this.resultType = resultType
    resultTypeStateFlow.update { resultType }
  }
}
