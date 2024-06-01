package com.skydoves.pokedex.type.element.model.datasource

import com.skydoves.pokedex.type.ElementData
import kotlinx.coroutines.flow.Flow

/**
 * Interface for a data source that provides a flow of element types.
 * Coded by Nguyen Dang Khoa (ID: 21110045).
 */
interface TypesDataSource {
  /**
   * Retrieves the list of element types as a flow.
   *
   * @return A flow emitting a list of element types.
   */
  fun retrieveTypes(): Flow<List<ElementData>>
}
