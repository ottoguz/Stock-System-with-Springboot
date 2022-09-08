package br.com.stockcontrol.bo;


import br.com.stockcontrol.model.Category;
import br.com.stockcontrol.model.Product;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductBOTest {

    @Autowired
    ProductBO productBO = new ProductBO();

    @Test
    @Order(1)
    public void insert() {
        Product product = new Product();
        product.setName("Laptop");
        product.setCategory(Category.COMPUTERS);
        product.setActive(true);
        productBO.insert(product);
    }

    @Test
    @Order(2)
    public void searchById() {
        Product product = productBO.searchById(1L);
        System.out.println(product);
    }

    @Test
    @Order(3)
    public void update() {
        Product product = productBO.searchById(1L);
        product.setName("Desktop");
    }
}
