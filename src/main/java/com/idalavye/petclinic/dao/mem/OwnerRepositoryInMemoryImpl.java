package com.idalavye.petclinic.dao.mem;

import com.idalavye.petclinic.dao.OwnerRepository;
import com.idalavye.petclinic.model.Owner;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository //Çalışma zamanında bir bean yaratacak
public class OwnerRepositoryInMemoryImpl implements OwnerRepository {

    private Map<Long,Owner> ownersMap = new HashMap<>();

    public OwnerRepositoryInMemoryImpl() {
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setFirstName("İbrahim");
        owner.setLastName("Dagdelen");

        Owner owner1 = new Owner();
        owner1.setId(2L);
        owner1.setFirstName("Ali");
        owner1.setLastName("Veli");

        Owner owner2 = new Owner();
        owner2.setId(3L);
        owner2.setFirstName("Hasan");
        owner2.setLastName("Hüsen");

        Owner owner4 = new Owner();
        owner4.setId(4L);
        owner4.setFirstName("Humeyra");
        owner4.setLastName("Sevindik");

        ownersMap.put(owner.getId(),owner);
        ownersMap.put(owner1.getId(),owner1);
        ownersMap.put(owner2.getId(),owner2);
        ownersMap.put(owner4.getId(),owner4);
    }

    @Override
    public List<Owner> findAll() {
        return new ArrayList<>(ownersMap.values());
    }

    @Override
    public Owner findById(Long id) {
        return ownersMap.get(id);
    }

    @Override
    public List<Owner> findByLastName(String lastName) {
        return ownersMap.values().stream().filter(owner -> owner.getLastName().equals(lastName)).collect(Collectors.toList());
    }

    @Override
    public void create(Owner owner) {
        owner.setId(new Date().getTime());
        ownersMap.put(owner.getId(),owner);
    }

    @Override
    public Owner update(Owner owner) {
        ownersMap.replace(owner.getId(),owner);
        return owner;
    }

    @Override
    public void delete(Long id) {
        ownersMap.remove(id);
    }
}
