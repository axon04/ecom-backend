package com.shockweb.db.domain.entity;

public enum AddressField {
    FIRST_LINE("firstLine"),
    SECOND_LINE("secondLine"),
    THIRD_LINE("thirdLine"),
    CITY("city"),
    REGION("region"),
    ZIPCODE("zipcode"),
    COUNTRY("country");

    private final String fieldName;

    AddressField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
