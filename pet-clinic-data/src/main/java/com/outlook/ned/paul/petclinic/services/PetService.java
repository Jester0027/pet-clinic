package com.outlook.ned.paul.petclinic.services;

import com.outlook.ned.paul.petclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
