package org.pet.shop.service;

import org.jetbrains.annotations.NotNull;
import org.pet.shop.dto.ParentsDTO;
import org.pet.shop.dto.PetDTO;
import org.pet.shop.entity.Pet;
import org.pet.shop.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
class PetshopServiceImp implements PetshopService {

    @Autowired
    private PetRepository petRepository;

    public int add(int a, int b) {
        return a + b;
    }

    @NotNull
    @Override
    public Pet newPet(@NotNull PetDTO petDTO) {
        var newPet = new Pet();
        newPet.setName(petDTO.getName());
        newPet.setType(petDTO.getType());
        newPet.setColor(petDTO.getColor());

        saveParents(petDTO, newPet);

        return petRepository.save(newPet);
    }

    private void saveParents(@NotNull PetDTO petDTO, Pet newPet) {
        if (petDTO.getParents() != null) {
            var parents = petDTO.getParents();
            if (!parents.getFather().isEmpty()) {
                var father = petRepository.findById(UUID.fromString(parents.getFather()));
                father.ifPresent(newPet::setFather);
            }
            if (!parents.getMother().isEmpty()) {
                var mother = petRepository.findById(UUID.fromString(parents.getMother()));
                mother.ifPresent(newPet::setMother);
            }
        }
    }

    @NotNull
    @Override
    public List<PetDTO> getAllPets() {
        var pets = petRepository.findAll();
        var petDTOs = new ArrayList<PetDTO>();
        for (Pet pet : pets) {
            petDTOs.add(new PetDTO(pet));
        }
        return petDTOs;
    }

    @NotNull
    @Override
    public Pet getPetById(@NotNull UUID id) {
        try {
            return petRepository.getReferenceById(id);
        } catch (Exception e) {
            return new Pet();
        }
    }

    @NotNull
    @Override
    public PetDTO updatePet(@NotNull UUID id, @NotNull PetDTO petDTO) {
        var pet = getPetById(id);
        if (pet.getName().isBlank()) {
            return new PetDTO();
        }
        pet.setColor(petDTO.getColor());
        pet.setName(petDTO.getName());
        pet.setType(petDTO.getType());
        saveParents(petDTO, pet);

        var result = petRepository.save(pet);
        return new PetDTO(result);
    }
}
