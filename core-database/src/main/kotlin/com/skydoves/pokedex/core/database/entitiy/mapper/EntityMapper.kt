

package me.sjihh.pokedex.core.database.entitiy.mapper

interface EntityMapper<Domain, Entity> {

  fun asEntity(domain: Domain): Entity

  fun asDomain(entity: Entity): Domain
}
