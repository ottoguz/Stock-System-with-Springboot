package br.com.stockcontrol.dao;

import br.com.stockcontrol.model.EntryNote;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EntryNoteDAO implements CRUD<EntryNote, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntryNote searchById(Long id) {
        return entityManager.find(EntryNote.class, id);
    }

    @Override
    public List<EntryNote> list() {
        Query query = entityManager.createQuery("select en from EntryNote en");
        return query.getResultList();
    }

    @Override
    public void insert(EntryNote entryNote) {
        entityManager.persist(entryNote);
    }

    @Override
    public void update(EntryNote entryNote) {
        entityManager.merge(entryNote);
    }

    @Override
    public void remove(EntryNote entryNote) {
        entityManager.remove(entryNote);
    }
}
