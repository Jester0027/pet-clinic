package com.outlook.ned.paul.petclinic.services.map;

import com.outlook.ned.paul.petclinic.model.Specialty;
import com.outlook.ned.paul.petclinic.model.Vet;
import com.outlook.ned.paul.petclinic.services.SpecialtyService;
import com.outlook.ned.paul.petclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        if (object.getSpecialties().size() > 0) {
            object.getSpecialties().forEach(s -> {
                if (s.getId() == null) {
                    Specialty savedSpecialty = specialtyService.save(s);
                    s.setId(savedSpecialty.getId());
                }
            });
        }

        return super.save(object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
