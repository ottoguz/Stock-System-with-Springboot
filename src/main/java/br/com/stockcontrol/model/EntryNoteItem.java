package br.com.stockcontrol.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "entry_note_items")
public class EntryNoteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @NotNull
    private Product product;

    @ManyToOne //relation 1:N
    @JoinColumn(name = "entry_note_id")
    @NotNull
    private EntryNote entryNote;

    @NotNull(message = "Inform quantity!")
    private Integer quantity;

    @NotNull(message = "Inform unit value!")
    private Float unitValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public EntryNote getEntryNote() {
        return entryNote;
    }

    public void setEntryNote(EntryNote entryNote) {
        this.entryNote = entryNote;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(Float unitValue) {
        this.unitValue = unitValue;
    }

    public Float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Float totalValue) {
        this.totalValue = totalValue;
    }

    private Float totalValue;
}
