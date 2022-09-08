package br.com.stockcontrol.controller;


import br.com.stockcontrol.bo.ProductBO;
import br.com.stockcontrol.model.Category;
import br.com.stockcontrol.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequestMapping(value = "/products" )
public class ProductController {

    @Autowired
    private ProductBO productBO;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView new_form(ModelMap model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", Arrays.asList(Category.values()));
        return new ModelAndView("/product/form", model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute Product product, BindingResult result, RedirectAttributes attr, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", Arrays.asList(Category.values()));
            return "product/form";
        }
        if (product.getId() == null) {
            productBO.insert(product);
            attr.addFlashAttribute("feedback", "Product successfully added!");
        }
        else {
            productBO.update(product);
            attr.addFlashAttribute("feedback", "Product updated successfully!");
        }
        return "redirect:/products";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView listProd(ModelMap model) {
        model.addAttribute("products", productBO.list());
        return new ModelAndView("/product/list");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        try {
            model.addAttribute("product", productBO.searchById(id));
            model.addAttribute("categories", Arrays.asList(Category.values()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("/product/form", model);
    }

    @RequestMapping(value = "/inactivate/{id}", method = RequestMethod.GET)
    public String inactivate(@PathVariable("id") Long id, RedirectAttributes attr) {
        try {
            Product product = productBO.searchById(id);
            productBO.deactivate(product);
            attr.addFlashAttribute("feedback", "Product successfully inactivated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/products";
    }

    @RequestMapping(value = "/activate/{id}", method = RequestMethod.GET)
    public String activate(@PathVariable("id") Long id, RedirectAttributes attr) {
        try {
            Product product = productBO.searchById(id);
            productBO.activate(product);
            attr.addFlashAttribute("feedback", "Product successfully activated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/products";
    }
}
