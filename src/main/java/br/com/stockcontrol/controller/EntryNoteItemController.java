package br.com.stockcontrol.controller;


import br.com.stockcontrol.bo.EntryNoteBO;
import br.com.stockcontrol.bo.EntryNoteItemBO;
import br.com.stockcontrol.bo.ProductBO;
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

@Controller
@RequestMapping("/entry-note-item")
public class EntryNoteItemController {

    @Autowired
    private ProductBO productBO;

    @Autowired
    private EntryNoteBO entryNoteBO;

    @Autowired
    private EntryNoteItemBO entryNoteItemBO;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute EntryNoteItem entryNoteItem, BindingResult result, RedirectAttributes attr, ModelMap model) {
        Long productId = entryNoteItem.getProduct().getId();
        if (productId == null) {
            result.rejectValue("product", "field.required");
        }

        if (entryNoteItemBO.alreadyAddedItem(entryNoteItem)) {
            result.rejectValue("product", "duplicate");
        }

        if (result.hasErrors()) {
            model.addAttribute("products", productBO.list());
            return "/entry-note-item/form";
        }
        EntryNote entryNote = entryNoteBO.searchById(entryNoteItem.getEntryNote().getId());
        entryNoteItem.setEntryNote(entryNote);

        if (entryNoteItem.getId() == null) {
            entryNoteItemBO.insert(entryNoteItem);
            attr.addFlashAttribute("feedback", "Product successfully added!");
        } else {
            entryNoteItemBO.update(entryNoteItem);
            attr.addFlashAttribute("feedback", "Product successfully updated!");
        }

        Long entryNoteId = entryNoteItem.getEntryNote().getId();
        return "redirect:/entry-note/edit/" + entryNoteId;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("entryNoteItem", entryNoteItemBO.searchById(id));
        model.addAttribute("products", productBO.list());
        return new ModelAndView("/entry-note-item/form", model);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") Long id, RedirectAttributes attr) {
        Long noteId = 0L;
        EntryNoteItem entryNoteItem = entryNoteItemBO.searchById(id);
        noteId = entryNoteItem.getEntryNote().getId();
        entryNoteItemBO.remove(entryNoteItem);
        attr.addFlashAttribute("feeback", "Entry note item successfully removed!");
        return "redirect:/entry-note/edit/" + noteId;
    }
}

