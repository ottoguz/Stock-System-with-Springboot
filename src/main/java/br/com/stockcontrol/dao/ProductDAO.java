package br.com.stockcontrol.dao;

import br.com.stockcontrol.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductDAO implements CRUD<Product, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product searchById(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> list() {
        Query query = entityManager.createQuery("select p from Product p");
        return query.getResultList();
    }

    @Override
    public void insert(Product product) {
        entityManager.persist(product);
    }

    @Override
    public void update(Product product) {
        entityManager.merge(product);
    }

    @Override
    public void remove(Product product) {
        entityManager.remove(product);
    }
}
