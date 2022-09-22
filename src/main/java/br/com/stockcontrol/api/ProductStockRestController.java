package br.com.stockcontrol.api;

import br.com.stockcontrol.bo.ProductBO;
import br.com.stockcontrol.bo.ProductStockBO;
import br.com.stockcontrol.model.Product;
import br.com.stockcontrol.model.ProductStock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductStockRestController {

    @Autowired
    private ProductStockBO productStockBO;

    @Autowired
    private ProductBO productBO;

    @RequestMapping(value = "/api/stock", method = RequestMethod.GET)
    public List<ProductStock> list() {
        return productStockBO.list();
    }

    @RequestMapping(value = "/api/stock/{id}", method = RequestMethod.GET)
    public ProductStock searchById(@PathVariable Long id) {
        return productStockBO.searchById(id);
    }

    @RequestMapping(value = "/api/stock", method = RequestMethod.POST)
    public ProductStock insert(@RequestBody ProductStock productStock) {
        Product product = productBO.searchById(productStock.getProduct().getId());
        productStock.setProduct(product);
        productStockBO.insert(productStock);
        return productStock;
    }

    @RequestMapping(value = "/api/stock/{id}", method = RequestMethod.PUT)
    public ProductStock update(@PathVariable Long id, @RequestBody  ProductStock productStock) {
        productStock.setId(id);
        productStock.setProduct(productBO.searchById(productStock.getProduct().getId()));
        productStockBO.update(productStock);
        return productStock;
    }

    @RequestMapping(value = "/api/stock/{id}", method = RequestMethod.DELETE)
    public ProductStock remove(@PathVariable Long id) {
       ProductStock productStock = productStockBO.searchById(id);
       productStockBO.remove(productStock);
       return productStock;
    }
}
