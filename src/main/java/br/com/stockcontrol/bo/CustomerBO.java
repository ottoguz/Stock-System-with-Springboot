//BO = business object (implements the business rules)
package br.com.stockcontrol.bo;

import br.com.stockcontrol.dao.CRUD;
import br.com.stockcontrol.dao.CustomerDAO;
import br.com.stockcontrol.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerBO implements CRUD<Customer, Long> {
    //dependency injection
    @Autowired
    private CustomerDAO dao;

    @Override
    public Customer searchById(Long id) {
        return dao.searchById(id);
    }

    @Override
    public List<Customer> list() {
        return dao.list();
    }

    @Override
    public void insert(Customer customer) {
        dao.insert(customer);
    }

    @Override
    public void update(Customer customer) {
        dao.update(customer);
    }

    @Override
    public void remove(Customer customer) {
        dao.remove(customer);
    }

    public void deactivate(Customer customer) {
        customer.setActive(false);
        dao.update(customer);
    }

    public void activate(Customer customer) {
        customer.setActive(true);
        dao.update(customer);
    }
}
