package br.com.stockcontrol.bo;

import br.com.stockcontrol.model.Supplier;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
 //continue at 30:05
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SupplierBOTest {

    @Autowired
    private SupplierBO supplierBO;

    @Test
    @Order(1)
    public void insert() {
        Supplier supplier = new Supplier();
        supplier.setCompanyName("Organizações Tabajara");
        supplier.setTradingName("Pharmácia do seu Creisson");
        supplier.setCnpj("41.444.337/0001-75");
        supplier.setEmail("tabajara@gmail.com");
        supplier.setPhone("9999-9999");
        supplier.setActive(true);
        supplierBO.insert(supplier);
    }

    @Test
    @Order(2)
    public void searchById() {
        Supplier supplier = supplierBO.searchById(1L);
        System.out.println(supplier);
    }

    @Test
    @Order(3)
    public void update() {
        Supplier supplier = supplierBO.searchById(1L);
        supplier.setCnpj("64.477.821/0001-04");
        supplierBO.update(supplier);
    }


}
