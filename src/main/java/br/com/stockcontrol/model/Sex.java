package br.com.stockcontrol.model;

public enum Sex {
    MALE("Male"),
    FEMALE("Female");

    private  String description;

    Sex(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
