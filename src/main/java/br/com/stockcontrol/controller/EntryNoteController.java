package br.com.stockcontrol.controller;

import br.com.stockcontrol.bo.EntryNoteBO;
import br.com.stockcontrol.bo.ProductBO;
import br.com.stockcontrol.bo.SupplierBO;
import br.com.stockcontrol.model.EntryNote;
import br.com.stockcontrol.model.EntryNoteItem;
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
@RequestMapping("/entry-note")
public class EntryNoteController {

    @Autowired
    private EntryNoteBO entryNoteBO;

    @Autowired
    private ProductBO productBO;

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
    public String save(@Valid @ModelAttribute EntryNote entryNote, BindingResult result, RedirectAttributes attr, ModelMap model) {

        if (entryNote.getSupplier().getId() == null) {
            result.rejectValue("supplier", "field.required");
        }

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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView listEntry(ModelMap model) {
        model.addAttribute("notes", entryNoteBO.list());
        return new ModelAndView("/entry-note/list", model);
    }

    @RequestMapping(value = "/{id}/item", method = RequestMethod.GET )
    public ModelAndView product(@PathVariable("id") Long id, ModelMap model) {
        EntryNoteItem eni = new EntryNoteItem();
        EntryNote en = entryNoteBO.searchById(id);
        eni.setEntryNote(en);
        model.addAttribute("entryNoteItem", eni);
        model.addAttribute("products", productBO.list());
        return new ModelAndView("/entry-note-item/form", model);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("entryNoteItem", new EntryNoteItem());
        model.addAttribute("suppliers", supplierBO.list());
        model.addAttribute("entryNote", entryNoteBO.searchById(id));
        return new ModelAndView("/entry-note/form", model);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") Long id, RedirectAttributes attr) {
        EntryNote en = entryNoteBO.searchById(id);
        entryNoteBO.remove(en);
        attr.addFlashAttribute("feedback", "Entry note successfully removed!");
        return "redirect:/entry-note";
    }
}
