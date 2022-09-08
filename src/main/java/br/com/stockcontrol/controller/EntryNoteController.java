package br.com.stockcontrol.controller;

import br.com.stockcontrol.bo.EntryNoteBO;
import br.com.stockcontrol.bo.SupplierBO;
import br.com.stockcontrol.model.EntryNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequestMapping(value = "/entry-note")
public class EntryNoteController {

    @Autowired
    private EntryNoteBO entryNoteBO;

    @Autowired
    private SupplierBO supplierBO;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView new_form(ModelMap model) {
        Long supplierId = null;
        model.addAttribute("entryNote", new EntryNote());
        model.addAttribute("suppliers", supplierBO.list());
        return new ModelAndView("/entry-note/form", model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute EntryNote entryNote, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/entry-note/form";
        }
        if (entryNote.getId() == null) {
            entryNoteBO.insert(entryNote);
            attr.addFlashAttribute("feedback", "Entry note was successfully added!");
        }
        else {
            entryNoteBO.update(entryNote);
            attr.addFlashAttribute("feedback", "Entry note was successfully updated!");
        }
        return "redirect:/entry-note";
    }
}
