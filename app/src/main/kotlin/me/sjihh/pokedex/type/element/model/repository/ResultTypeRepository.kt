package me.sjihh.pokedex.type.element.model.repository

import me.sjihh.pokedex.type.element.model.datasource.ResultTypeDataSource
import javax.inject.Inject

/**
 * Repository class for managing the result type data.
 * Retrieves the result type from the data source.
 */
class ResultTypeRepository @Inject constructor(
  currentTypesDataSource: ResultTypeDataSource
) {
  // Flow representing the result type data
  val resultTypeFlow = currentTypesDataSource.retrieveResultType()
}
