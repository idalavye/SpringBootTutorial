package com.idalavye.petclinic.dao.jpa;

import com.idalavye.petclinic.dao.OwnerRepository;
import com.idalavye.petclinic.model.Owner;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("ownerRepository")
public class OwnerRepositoryJpaImpl implements OwnerRepository {

    /**
     *  @PersistenceContext
     *  Spring conteinerın runtime'da yönettiği transactional entity manager instance'ının buraya enjekte edilmesini sağlar
     **/
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Owner> findAll() {
        return entityManager.createQuery("from Owner",Owner.class).getResultList();
    }

    @Override
    public Owner findById(Long id) {
        return entityManager.find(Owner.class,id);
    }

    @Override
    public List<Owner> findByLastName(String lastName) {
        return entityManager.createQuery("from Owner where lastName = :lastName",Owner.class)
                .setParameter("lastName",lastName).getResultList();
    }

    @Override
    public void create(Owner owner) {
        entityManager.persist(owner);
    }

    @Override
    public Owner update(Owner owner) {
        return entityManager.merge(owner);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
        //entityManager.remove(entityManager.getReference(Owner.class,id));
    }
}
