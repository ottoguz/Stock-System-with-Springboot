package br.com.stockcontrol.dao;

import br.com.stockcontrol.model.EntryNoteItem;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EntryNoteItemDAO implements CRUD<EntryNoteItem, Long>{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntryNoteItem searchById(Long id) {
        return entityManager.find(EntryNoteItem.class, id);
    }

    @Override
    public List<EntryNoteItem> list() {
        Query query = entityManager.createNamedQuery("select eni from EntryNoteItem eni");
        return query.getResultList();
    }

    @Override
    public void insert(EntryNoteItem entryNoteItem) {
        entityManager.persist(entryNoteItem);
    }

    @Override
    public void update(EntryNoteItem entryNoteItem) {
        entityManager.merge(entryNoteItem);
    }

    @Override
    public void remove(EntryNoteItem entryNoteItem) {
        entityManager.remove(entryNoteItem);
    }

    public List<EntryNoteItem> listItemsNote(Long entryNoteId) {
        Query query = entityManager.createQuery("from EntryNoteItem e where e.entryNote.id = :entryNoteId")
                .setParameter("entryNoteId", entryNoteId);
        return query.getResultList();
    }
}
