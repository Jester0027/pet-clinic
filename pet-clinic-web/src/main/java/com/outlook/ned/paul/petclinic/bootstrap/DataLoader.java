package com.outlook.ned.paul.petclinic.bootstrap;

import com.outlook.ned.paul.petclinic.model.Owner;
import com.outlook.ned.paul.petclinic.model.Vet;
import com.outlook.ned.paul.petclinic.services.OwnerService;
import com.outlook.ned.paul.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 3; i++) {
            Owner owner = new Owner();
            owner.setFirstName("owner");
            owner.setLastName(""+i);
            owner.setId((long) i);
            ownerService.save(owner);

            Vet vet = new Vet();
            vet.setFirstName("Vet");
            vet.setLastName(""+i);
            vet.setId((long) i);
            vetService.save(vet);
        }

        System.out.println("Loaded owners and vets");
        for (int i = 0; i < 3; i++) {
            Vet vet = vetService.findById((long) i);
            System.out.println("---");
            System.out.println(vet.getFirstName() + " " + vet.getLastName());
        }
    }
}
