package br.com.stockcontrol.bo;

import br.com.stockcontrol.dao.CRUD;
import br.com.stockcontrol.dao.EntryNoteItemDAO;
import br.com.stockcontrol.model.EntryNoteItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EntryNoteItemBO implements CRUD<EntryNoteItem, Long> {

    @Autowired
    private EntryNoteItemDAO entryNoteItemDAO;

    @Override
    public EntryNoteItem searchById(Long id) {
        return entryNoteItemDAO.searchById(id);
    }

    @Override
    public List<EntryNoteItem> list() {
        return entryNoteItemDAO.list();
    }

    @Override
    public void insert(EntryNoteItem entryNoteItem) {
        entryNoteItemDAO.insert(entryNoteItem);
    }

    @Override
    public void update(EntryNoteItem entryNoteItem) {
        entryNoteItemDAO.update(entryNoteItem);
    }

    @Override
    public void remove(EntryNoteItem entryNoteItem) {
        entryNoteItemDAO.remove(entryNoteItem);
    }

    public boolean alreadyAddedItem(EntryNoteItem entryNoteItem) {
        Long entryNoteId = entryNoteItem.getEntryNote().getId();
        List<EntryNoteItem> items = entryNoteItemDAO.listItemsNote(entryNoteId);
        
        Long productId = entryNoteItem.getProduct().getId();
        
        if (entryNoteItem.getId() == null) {
            for (EntryNoteItem item : items) {
                if (item.getProduct().getId() == productId) {
                    return true;
                }
            }
        } else {
            Long entryNoteItemId = entryNoteItem.getId();
            for (EntryNoteItem item : items) {
                if (item.getProduct().getId() == productId && entryNoteItemId == item.getId()) {
                    return true;
                }
            }
        }
        return false;
    }
}
