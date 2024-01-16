package org.pet.shop.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ParentsDTO(

    @JsonProperty("mother")
    var mother: String = "",

    @JsonProperty("father")
    var father: String = "",
)