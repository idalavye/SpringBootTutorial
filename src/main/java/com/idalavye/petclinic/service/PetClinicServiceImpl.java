package com.idalavye.petclinic.service;

import com.idalavye.petclinic.dao.OwnerRepository;
import com.idalavye.petclinic.dao.PetRepository;
import com.idalavye.petclinic.exception.OwnerNotFoundException;
import com.idalavye.petclinic.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Transactional(rollbackFor=Exception.class)
 * Bu değişiklik ile checked exception oluştuğu zaman bile transaction işleminin roll back yapmasını belirttik.
 */
@Service
@Transactional(rollbackFor = Exception.class) //sınıf düzeyinde transactional kullanımında public metotların hepsini kapsar
public class PetClinicServiceImpl implements PetClinicService {

    private OwnerRepository ownerRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Secured(value = {"ROLE_USER","ROLE_EDITOR"})
    public List<Owner> findOwners() {
        return ownerRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Owner> findOwners(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Owner findOwner(Long id) throws OwnerNotFoundException {
        Owner owner = ownerRepository.findById(id);
        if(owner == null) throw new OwnerNotFoundException("Owner not found with id : " + id);
        return owner;
    }

    @Override
    public void createOwner(Owner owner) {
        ownerRepository.create(owner);
    }

    @Override
    public void updateOwner(Owner owner) {
        ownerRepository.update(owner);
    }

    @Override
    public void deleteOwner(Long id) {
        petRepository.deleteByOwnerId(id);
        ownerRepository.delete(id);

        //if(true) throw new RuntimeException("testing rollback");
    }
}
