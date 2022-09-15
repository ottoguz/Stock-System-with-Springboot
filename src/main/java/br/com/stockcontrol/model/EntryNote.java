package br.com.stockcontrol.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
@Entity
@Table(name = "entry_note")
public class EntryNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    @Column(nullable = false, name = "date_time", columnDefinition = "DATETIME")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @OneToMany(mappedBy = "entryNote", cascade = CascadeType.ALL)
    private List<EntryNoteItem> items;


    @Transient //will not be stored in the DB
    private Float total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Float getTotal() {
        this.total = 0f;
        if (this.items != null) {
            for (EntryNoteItem entryNoteItem : items) {
                total += entryNoteItem.getTotalValue();
            }
        }
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public List<EntryNoteItem> getItems() {
        return items;
    }

    public void setItems(List<EntryNoteItem> items) {
        this.items = items;
    }

}
