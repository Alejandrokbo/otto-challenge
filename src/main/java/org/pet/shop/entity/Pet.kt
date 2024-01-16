package org.pet.shop.entity

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import java.util.*

@Entity
data class Pet(

    @Id
    val id: UUID = UUID.randomUUID(),

    @JsonProperty("name")
    var name: String = "",

    @JsonProperty("type")
    var type: String = "",

    @JsonProperty("color")
    var color: String = "",

    @ManyToOne
    @JoinColumn(name = "mother_id")
    var mother: Pet? = null,

    @OneToMany(mappedBy = "mother", cascade = [CascadeType.ALL], orphanRemoval = true)
    val childrenWithMother: MutableList<Pet> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "father_id")
    var father: Pet? = null,

    @OneToMany(mappedBy = "father", cascade = [CascadeType.ALL], orphanRemoval = true)
    val childrenWithFather: MutableList<Pet> = mutableListOf()
)