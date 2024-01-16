package org.pet.shop.service

import org.pet.shop.dto.PetDTO
import org.pet.shop.entity.Pet
import java.util.*

interface PetshopService {

    fun newPet(petDTO: PetDTO): Pet

    fun getAllPets(): List<PetDTO>

    fun getPetById(id: UUID): Pet

    fun updatePet(id: UUID, petDTO: PetDTO): PetDTO
}