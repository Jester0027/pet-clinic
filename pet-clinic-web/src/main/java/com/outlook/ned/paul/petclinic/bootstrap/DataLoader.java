package com.outlook.ned.paul.petclinic.bootstrap;

import com.outlook.ned.paul.petclinic.model.Owner;
import com.outlook.ned.paul.petclinic.model.PetType;
import com.outlook.ned.paul.petclinic.model.Vet;
import com.outlook.ned.paul.petclinic.services.OwnerService;
import com.outlook.ned.paul.petclinic.services.PetTypeService;
import com.outlook.ned.paul.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        for (int i = 0; i < 3; i++) {
            Owner owner = new Owner();
            owner.setFirstName("owner");
            owner.setLastName(""+i);
            ownerService.save(owner);

            Vet vet = new Vet();
            vet.setFirstName("Vet");
            vet.setLastName(""+i);
            vetService.save(vet);
        }

        System.out.println("Loaded owners and vets");
        for (long i = 0; i < 3; i++) {
            Vet vet = vetService.findById(i);
            System.out.println("---");
            System.out.println(vet.getFirstName() + " " + vet.getLastName());
        }
    }
}
