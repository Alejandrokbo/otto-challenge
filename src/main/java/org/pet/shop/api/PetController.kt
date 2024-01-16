package org.pet.shop.api

import org.pet.shop.dto.ParentsDTO
import org.pet.shop.dto.PetDTO
import org.pet.shop.service.PetshopService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/pets")
class PetController {

    @Autowired
    lateinit var petService: PetshopService

    @PostMapping("/create")
    fun createPet(@RequestBody pet: PetDTO): ResponseEntity<String> {
        petService.newPet(pet)
        return ResponseEntity<String>("Pet ${pet.name} created", HttpStatus.OK)
    }

    @PutMapping("/update/{id}")
    fun updatePet(@PathVariable id: UUID, @RequestBody petDTO: PetDTO): ResponseEntity<PetDTO> {
        val petUpdated = petService.updatePet(id, petDTO)
        return ResponseEntity<PetDTO>(petUpdated, if (petUpdated.name.isNotBlank()) HttpStatus.OK else HttpStatus.NOT_FOUND)
    }

    @GetMapping("/all")
    fun getAllPets(): ResponseEntity<List<PetDTO>> {
        return ResponseEntity<List<PetDTO>>(petService.getAllPets(), HttpStatus.OK)
    }

    @GetMapping("/pet/{id}")
    fun getPetById(@PathVariable id: UUID): ResponseEntity<PetDTO> {
        val pet = petService.getPetById(id)
        return ResponseEntity<PetDTO>(PetDTO(pet), if (pet.name.isNotBlank()) HttpStatus.OK else HttpStatus.NOT_FOUND)
    }

    @GetMapping("/populate")
    fun populateDatabase(): ResponseEntity<String> {
        // Creating Tom
        val tom = PetDTO(
            name = "Tom",
            type = "cat",
            color = "black",
            parents = null
        )
        val tomResp = petService.newPet(tom)

        // Creating Milly
        val milly = PetDTO(
            name = "Milly",
            type = "cat",
            color = "brown",
            parents = null
        )
        val millyResp = petService.newPet(milly)

        // Creating Katy Purry with parents Tom and Milly
        val pet3 = PetDTO(
            name = "Katy Purry",
            type = "cat",
            color = "brown",
            parents = ParentsDTO(
                mother = millyResp.id.toString(),
                father = tomResp.id.toString()
            )
        )
        petService.newPet(pet3)
        return ResponseEntity<String>("Database populated", HttpStatus.OK)
    }
}