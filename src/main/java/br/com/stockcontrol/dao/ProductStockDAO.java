package br.com.stockcontrol.dao;

import br.com.stockcontrol.model.ProductStock;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductStockDAO implements CRUD<ProductStock, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ProductStock searchById(Long id) {
        return entityManager.find(ProductStock.class, id);
    }

    @Override
    public List<ProductStock> list() {
        Query query = entityManager.createNativeQuery("selecet ps from ProductStock ps");
        return query.getResultList();
    }

    @Override
    public void insert(ProductStock productStock) {
        entityManager.persist(productStock);
    }

    @Override
    public void update(ProductStock productStock) {
        entityManager.merge(productStock);
    }

    @Override
    public void remove(ProductStock productStock) {
        entityManager.remove(productStock);
    }
}
