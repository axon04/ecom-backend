package com.shockweb.db.domain.entity;

public enum UserField {
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    PASSWORD("password"),
    PHONE("phone");

    private final String fieldName;

    UserField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}


//public <T> void partialUpdateUser(String email, UserField field, T newValue) {
//    // Validate the type of newValue against the fieldType
//    if (!field.getFieldType().isInstance(newValue)) {
//        throw new IllegalArgumentException("Invalid type for " + field + ". Expected " + field.getFieldType() + " but got " + newValue.getClass());
//    }
//    // Update the user field with the new value
//    // ...
//}

//public enum UserField {
//    FIRST_NAME("firstName", String.class),
//    LAST_NAME("lastName", String.class),
//    PASSWORD("password", String.class),
//    PHONE("phone", String.class);
//
//    private final String fieldName;
//    private final Class<?> fieldType;
//
//    UserField(String fieldName, Class<?> fieldType) {
//        this.fieldName = fieldName;
//        this.fieldType = fieldType;
//    }
//
//    public String getFieldName() {
//        return fieldName;
//    }
//
//    public Class<?> getFieldType() {
//        return fieldType;
//    }
//}