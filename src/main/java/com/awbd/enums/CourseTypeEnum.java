package com.awbd.enums;

public enum CourseTypeEnum {
    SELECT ("SELECT"),
    INSERT ("INSERT"),
    UPDATE ("UPDATE"),
    DELETE ("DELETE");

    private String description;

    public String getDescription() {
        return description;
    }

    CourseTypeEnum(String description) {
        this.description = description;
    }
}
