package org.pet.shop.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.pet.shop.entity.Pet

data class PetDTO(
    @JsonProperty("id")
    var id: String = "",

    @JsonProperty("name")
    var name: String = "",

    @JsonProperty("type")
    var type: String = "",

    @JsonProperty("color")
    var color: String = "",

    @JsonProperty("parents")
    var parents: ParentsDTO? = null,
) {
    constructor(pet: Pet) : this(
        pet.id.toString(),
        pet.name,
        pet.type,
        pet.color,
        ParentsDTO(
            pet.mother?.name ?: "",
            pet.father?.name ?: ""
        )
    )
}