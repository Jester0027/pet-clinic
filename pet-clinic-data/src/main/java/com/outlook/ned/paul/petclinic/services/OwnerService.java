package com.outlook.ned.paul.petclinic.services;

import com.outlook.ned.paul.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
