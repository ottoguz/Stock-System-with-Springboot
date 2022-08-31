package br.com.stockcontrol.dao;

import br.com.stockcontrol.model.Supplier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository //data access class
@Transactional
public class SupplierDAO implements CRUD<Supplier, Long>{

    @PersistenceContext //Hibernate class to persist data
    private EntityManager entityManager;

    @Override
    public Supplier searchById(Long id) {
        return entityManager.find(Supplier.class, id);
    }

    @Override
    public List<Supplier> list() {
        Query query = entityManager.createQuery("select s from Supplier s");
        return query.getResultList(); //returns the list of objects in the DB
    }

    @Override
    public void insert(Supplier supplier) {
        entityManager.persist(supplier);
    }

    @Override
    public void update(Supplier supplier) {
        entityManager.merge(supplier);
    }

    @Override
    public void remove(Supplier supplier) {
        entityManager.remove(supplier);
    }
}
