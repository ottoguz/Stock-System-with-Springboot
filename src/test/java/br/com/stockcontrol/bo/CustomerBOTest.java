//Create methods to be tested (YOU WON'T BE ABLE TO TEST CLASS WITHOUT THE ANNOTATIONS @s)
package br.com.stockcontrol.bo;

import br.com.stockcontrol.model.Customer;
import br.com.stockcontrol.model.Sex;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@SpringBootTest //creates context for test to be exe
@ExtendWith(SpringExtension.class) //Informs JUinit that it's being tested in ths spring env
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerBOTest {

    @Autowired //dependency injection(spring manages object lifecycle)
    private CustomerBO bo; //class to be tested

    //test methods
    //test INSERT
    @Test //Test method
    @Order(1)
    public void insert() {
        Customer customer = new Customer();
        customer.setName("José da Silva");
        customer.setCpf("01234567890");
        customer.setBirthDate(LocalDate.of(2000, 1, 8));
        customer.setSex(Sex.MALE);
        customer.setPhone("0123456789");
        customer.setCell("01234567890");
        customer.setEmail("jose.123@gmail.com");
        customer.setActive(true);
        bo.insert(customer);
    }

    //test SEARCHBYID
    @Test //Test method
    @Order(2)
    public void searchById() {
        Customer customer = bo.searchById(1L); //(1L) = id to be searched
        System.out.println(customer); //show on console
    }

    //test UPDATE
    @Test //Test method
    @Order(3)
    public void update() {
        Customer customer = bo.searchById(1L); //(1L) = id to be searched
        customer.setCpf("98765432100"); //updated cpf
        bo.update(customer); //method to update
    }
}
