package me.sjihh.pokedex.type.element.model.datasource

import me.sjihh.pokedex.ResultType
import kotlinx.coroutines.flow.Flow
/**
 * Interface for a data source that provides a flow of result types and allows updating the result type.
 */
interface ResultTypeDataSource {
  /**
   * Retrieves the current result type as a flow.
   *
   * @return A flow emitting the current result type.
   */
  fun retrieveResultType(): Flow<ResultType>

  /**
   * Updates the result type with the provided value.
   *
   * @param resultType The new result type to be updated.
   */
  fun updateResultType(resultType: ResultType)
}
