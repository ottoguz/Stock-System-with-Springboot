package br.com.stockcontrol.bo;

import br.com.stockcontrol.dao.CRUD;
import br.com.stockcontrol.dao.ProductDAO;
import br.com.stockcontrol.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductBO implements CRUD<Product, Long> {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public Product searchById(Long id) {
        return productDAO.searchById(id);
    }

    @Override
    public List<Product> list() {
        return productDAO.list();
    }

    @Override
    public void insert(Product product) {
        productDAO.insert(product);
    }

    @Override
    public void update(Product product) {
        productDAO.update(product);
    }

    @Override
    public void remove(Product product) {
        productDAO.remove(product);
    }

    public void activate(Product product) {
        product.setActive(true);
        productDAO.update(product);
    }

    public void deactivate(Product product) {
        product.setActive(false);
        productDAO.update(product);
    }
}


//create unit tests and continue AULA 2 31:24