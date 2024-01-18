package org.pet.shop.api

import org.pet.shop.dto.*
import org.pet.shop.service.PetshopService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/pets")
class PetController {

    @Autowired
    lateinit var petService: PetshopService

    @PostMapping("/create")
    fun createPet(@RequestBody pet: PetDTO): ResponseEntity<ApiResponse> {
        val createdPet = petService.newPet(pet)
        return response(ApiResponse(message = "Pet ${createdPet.name} created", status = StatusResponse.OK.name, data = pet))
    }

    @PutMapping("/update/{id}")
    fun updatePet(@PathVariable id: UUID, @RequestBody petDTO: PetDTO): ResponseEntity<ApiResponse> {
        val petUpdated = petService.updatePet(id, petDTO)
        return if (petUpdated.id.isNotBlank()) {
            response(ApiResponse(message = "Pet ${petUpdated.name} updated", status = StatusResponse.OK.name, data = petUpdated))
        } else {
            response(ApiResponse(message = "Pet not found", status = StatusResponse.NOT_FOUND.name))
        }
    }

    @GetMapping("/all")
    fun getAllPets(): ResponseEntity<ApiResponse> {
        val pets = petService.getAllPets()
        return response(ApiResponse(message = "Showing ${pets.size} pets.", status = StatusResponse.OK.name, data = pets))
    }

    @GetMapping("/pet/{id}")
    fun getPetById(@PathVariable id: UUID): ResponseEntity<ApiResponse> {
        val pet = petService.getPetById(id)
        return if (pet.name.isNotBlank()) {
            response(ApiResponse(message = "Showing pet ${pet.name}", status = StatusResponse.OK.name, data = PetDTO(pet)))
        } else {
            response(ApiResponse(message = "Pet not found", status = StatusResponse.NOT_FOUND.name))
        }
    }

    @GetMapping("/populate")
    fun populateDatabase(): ResponseEntity<ApiResponse> {
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
        return response(ApiResponse(message = "Database populated", status = StatusResponse.OK.name))
    }
}