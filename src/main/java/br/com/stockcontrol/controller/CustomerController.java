package br.com.stockcontrol.controller;

import br.com.stockcontrol.bo.CustomerBO;
import br.com.stockcontrol.model.Customer;
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
    public String save(@Valid @ModelAttribute Customer customer, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors())
            return "customer/form";
        if (customer.getId() == null) {
            bo.insert(customer);
            attr.addFlashAttribute("feedback", "Customer successfully added!");
        }
        else {
            bo.update(customer);
            attr.addFlashAttribute("feedback", "Customer successfully updated!");
        }
        return "redirect:/customers";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView listCust(ModelMap model) {
        model.addAttribute("customers", bo.list());
        return new ModelAndView("/customer/list", model);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        try {
            model.addAttribute("customer", bo.searchById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("/customer/form", model);
    }

    @RequestMapping(value = "/inactivate/{id}", method = RequestMethod.GET)
    public String inactivate(@PathVariable("id") Long id, RedirectAttributes attr) {
        try {
            Customer customer = bo.searchById(id);
            bo.deactivate(customer);
            attr.addFlashAttribute("feedback", "Customer successfully inactivated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/customers";
    }

    @RequestMapping(value = "/activate/{id}", method = RequestMethod.GET)
    public String activate(@PathVariable("id") Long id, RedirectAttributes attr) {
        try {
            Customer customer = bo.searchById(id);
            bo.activate(customer);
            attr.addFlashAttribute("feedback", "Customer successfully Activated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/customers";
    }
}
