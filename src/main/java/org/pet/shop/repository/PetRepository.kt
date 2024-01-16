package org.pet.shop.repository

import org.pet.shop.entity.Pet
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface PetRepository : JpaRepository<Pet, UUID> {}