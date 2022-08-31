package br.com.stockcontrol.bo;

import br.com.stockcontrol.dao.CRUD;
import br.com.stockcontrol.dao.SupplierDAO;
import br.com.stockcontrol.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierBO implements CRUD<Supplier, Long> {

    @Autowired //Data access class
    private SupplierDAO dao;

    @Override
    public Supplier searchById(Long id) {
        return dao.searchById(id);
    }

    @Override
    public List<Supplier> list() {
        return dao.list();
    }

    @Override
    public void insert(Supplier supplier) {
        dao.insert(supplier);
    }

    @Override
    public void update(Supplier supplier) {
        dao.update(supplier);
    }

    @Override
    public void remove(Supplier supplier) {
        dao.remove(supplier);
    }

    public void deactivate(Supplier supplier) {
        supplier.setActive(false);
        dao.update(supplier);
    }

    public void activate(Supplier supplier) {
        supplier.setActive(true);
        dao.update(supplier);
    }
}
