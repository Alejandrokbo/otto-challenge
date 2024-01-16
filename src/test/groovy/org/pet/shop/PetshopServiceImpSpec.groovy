package org.pet.shop

import org.pet.shop.dto.ParentsDTO
import org.pet.shop.dto.PetDTO
import org.pet.shop.entity.Pet
import org.pet.shop.service.PetshopService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class PetshopServiceImpSpec extends Specification {

    @Autowired
    private PetshopService petshopService

    def "should create a pet"() {
        given: 'a pet'
        PetDTO pet = new PetDTO()
        pet.name = "Fido"
        pet.type = "Dog"
        pet.color = "Brown"

        when: 'create is called'
        Pet createdPet = petshopService.newPet(pet)

        then: 'the pet is created'
        createdPet.id != null
        createdPet.name == "Fido"
        createdPet.type == "Dog"
        createdPet.color == "Brown"

    }
}
