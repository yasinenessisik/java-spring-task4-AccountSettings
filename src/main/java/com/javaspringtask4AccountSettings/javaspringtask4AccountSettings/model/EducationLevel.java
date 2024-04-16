package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model;

public enum EducationLevel {
    NONE("Yok"),
    PRIMARY_SCHOOL("Ilk Okul"),
    MIDDLE_SCHOOL("Orta okul"),
    HIGH_SCHOOL("Lise"),
    ASSOCIATE("On Lisans"),
    BACHELOR("Lisans"),
    MASTER("Master"),
    DOCTORATE("Doktora");

    private final String label;

    private EducationLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

