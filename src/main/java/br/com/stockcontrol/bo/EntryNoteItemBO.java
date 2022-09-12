package br.com.stockcontrol.bo;

import br.com.stockcontrol.dao.CRUD;
import br.com.stockcontrol.dao.EntryNoteItemDAO;
import br.com.stockcontrol.model.EntryNoteItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
