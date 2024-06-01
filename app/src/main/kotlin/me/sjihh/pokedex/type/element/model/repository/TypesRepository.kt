package me.sjihh.pokedex.type.element.model.repository

import me.sjihh.pokedex.asResultType
import me.sjihh.pokedex.type.ElementData
import me.sjihh.pokedex.type.element.model.datasource.ResultTypeDataSource
import me.sjihh.pokedex.type.element.model.datasource.TypesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Repository class for managing elemental types data.
 * Retrieves elemental types from the data source and updates the current types in the result type.
 * Coded by Nguyen Dang Khoa (ID: 21110045).
 */
class TypesRepository @Inject constructor(
  typesDataSource: TypesDataSource,
  private val currentTypesDataSource: ResultTypeDataSource
) {
  private var types: List<ElementData> = emptyList()

  // Flow representing the elemental types data
  val typesFlow: Flow<List<ElementData>> = typesDataSource.retrieveTypes()
    .onEach { types = it }

  /**
   * Adds selected elemental types to the current result type.
   * @param indices Indices of the selected elemental types.
   */
  fun addTypesToResultTypeTheTypesAt(indices: List<Int>) {
    // Retrieve selected elemental types from the list
    val types = indices.mapNotNull { index -> types.getOrNull(index) }

    // Update the current result type with the selected types
    currentTypesDataSource.updateResultType(resultType = types.asResultType())
  }
}
