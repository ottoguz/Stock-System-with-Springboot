package br.com.stockcontrol.model;

public enum Category {
    CELLPHONES("Cell phones"),
    APPLIANCES("Appliances"),
    COMPUTERS("Computer Parts"),
    FURNITURE("Furniture");

    private String description;
    Category(String description) {
        this.description = description;
    }

    public  String getDescription() {
        return this.description;
    }
}
