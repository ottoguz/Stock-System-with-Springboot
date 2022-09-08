package br.com.stockcontrol.bo;

import br.com.stockcontrol.dao.CRUD;
import br.com.stockcontrol.dao.EntryNoteDAO;
import br.com.stockcontrol.model.EntryNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryNoteBO implements CRUD<EntryNote, Long> {

    @Autowired
    private EntryNoteDAO entryNoteDAO;

    @Override
    public EntryNote searchById(Long id) {
        return entryNoteDAO.searchById(id);
    }

    @Override
    public List<EntryNote> list() {
        return entryNoteDAO.list();
    }

    @Override
    public void insert(EntryNote entryNote) {
        entryNoteDAO.insert(entryNote);
    }

    @Override
    public void update(EntryNote entryNote) {
        entryNoteDAO.update(entryNote);
    }

    @Override
    public void remove(EntryNote entryNote) {
        entryNoteDAO.remove(entryNote);
    }
}
