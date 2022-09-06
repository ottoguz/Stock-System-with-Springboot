package br.com.stockcontrol.controller;

import br.com.stockcontrol.bo.SupplierBO;
import br.com.stockcontrol.model.Supplier;
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
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierBO supplierBO;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView new_form(ModelMap model) {
        model.addAttribute("supplier", new Supplier());
        return new ModelAndView("/supplier/form", model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute Supplier supplier, BindingResult result, RedirectAttributes attr) {
        if(result.hasErrors()) {
            return "supplier/form";
        }
        if(supplier.getId() == null) {
            supplierBO.insert(supplier);
            attr.addFlashAttribute("feedback", "Supplier successfully added!");
        }
        else {
            supplierBO.update(supplier);
            attr.addFlashAttribute("feedback", "Supplier successfully updated");
        }
        return "redirect:/suppliers";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView listSup(ModelMap model) {
        model.addAttribute("suppliers", supplierBO.list());
        return new ModelAndView("/supplier/list", model);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("supplier", supplierBO.searchById(id));
        return new ModelAndView("/supplier/form", model);
    }

    @RequestMapping(value = "/supplier/inactivate", method = RequestMethod.GET)
    public String inactivate(@PathVariable("id") Long id, RedirectAttributes attr) {
        try {
            Supplier supplier = supplierBO.searchById(id);
            supplierBO.deactivate(supplier);
            attr.addFlashAttribute("feedback", "Supplier successfully inactivated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/suppliers";
    }

    @RequestMapping(value = "/supplier/activate", method = RequestMethod.GET)
    public String activate(@PathVariable("id") Long id, RedirectAttributes attr) {
        try {
            Supplier supplier = supplierBO.searchById(id);
            supplierBO.activate(supplier);
            attr.addFlashAttribute("feedback", "Supplier Successfully activated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  "redirect:/suppliers";
    }
}
