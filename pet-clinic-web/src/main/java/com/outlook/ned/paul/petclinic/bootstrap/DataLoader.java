package com.outlook.ned.paul.petclinic.bootstrap;

import com.outlook.ned.paul.petclinic.model.*;
import com.outlook.ned.paul.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final PetService petService;
    private final SpecialtyService specialtyService;

    public DataLoader(
            OwnerService ownerService,
            VetService vetService,
            PetTypeService petTypeService,
            PetService petService,
            SpecialtyService specialtyService
    ) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);


        for (int i = 0; i < 3; i++) {

            Owner owner = new Owner();
            owner.setFirstName("owner");
            owner.setLastName("" + i);
            owner.setAddress("Address " + (i + 1));
            owner.setCity("City");
            owner.setTelephone("+" + (i + 1) + "23 45 67 89");
            ownerService.save(owner);

            Pet pet = new Pet();
            pet.setPetType(Math.random() > 0.5 ? savedDogPetType : savedCatPetType);
            pet.setBirthDate(LocalDate.now());
            pet.setOwner(owner);
            pet.setName("pet " + i);
            Pet savedPet = petService.save(pet);
            owner.getPets().add(savedPet);

            Vet vet = new Vet();
            vet.setFirstName("Vet");
            vet.setLastName("" + i);
            vet.getSpecialties().add(Math.random() > 0.66
                    ? savedDentistry
                    : Math.random() > 0.5
                    ? savedRadiology
                    : savedSurgery
            );
            vetService.save(vet);
        }

        System.out.println("Loaded owners and vets");
        for (long i = 0; i < 3; i++) {
            Vet vet = vetService.findById(i);
            System.out.println("---");
            System.out.println(vet.getFirstName() + " " + vet.getLastName() + " --- " + vet.getSpecialties());
        }
    }
}
