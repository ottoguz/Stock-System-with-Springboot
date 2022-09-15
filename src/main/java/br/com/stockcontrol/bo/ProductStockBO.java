package br.com.stockcontrol.bo;

import br.com.stockcontrol.dao.CRUD;
import br.com.stockcontrol.dao.ProductStockDAO;
import br.com.stockcontrol.model.ProductStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductStockBO implements CRUD<ProductStock, Long> {

    @Autowired
    private ProductStockDAO productStockDAO;

    @Override
    public ProductStock searchById(Long id) {
        return productStockDAO.searchById(id);
    }

    @Override
    public List<ProductStock> list() {
        return productStockDAO.list();
    }

    @Override
    public void insert(ProductStock productStock) {
        productStockDAO.insert(productStock);
    }

    @Override
    public void update(ProductStock productStock) {
        productStockDAO.update(productStock);
    }

    @Override
    public void remove(ProductStock productStock) {
        productStockDAO.remove(productStock);
    }
}
