//Contains the database access methods(persistence)
package br.com.stockcontrol.dao;

import br.com.stockcontrol.model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CustomerDAO implements CRUD<Customer, Long>{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer searchById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public List<Customer> list() {
        Query query = entityManager.createQuery("Select c from Customer c");
        return (List<Customer>) query.getResultList();
    }

    @Override
    public void insert(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public void update(Customer customer) {
        entityManager.merge(customer);
    }

    @Override
    public void remove(Customer customer) {
        entityManager.remove(customer);
    }
}
