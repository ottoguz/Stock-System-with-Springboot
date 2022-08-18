package br.com.stockcontrol.controller;

import br.com.stockcontrol.bo.CustomerBO;
import br.com.stockcontrol.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customers") // url path to activate controller
public class CustomerController {

    @Autowired
    private CustomerBO bo;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView new_form(ModelMap model) {
        model.addAttribute("customer", new Customer());
        return new ModelAndView("/customer/form", model);
    } //method to show a blank form
    @RequestMapping(value = "", method = RequestMethod.POST) //without this data will not be saved
    public String save(@ModelAttribute Customer customer) {
        bo.insert(customer);
        return "customer/form";
    }
}
