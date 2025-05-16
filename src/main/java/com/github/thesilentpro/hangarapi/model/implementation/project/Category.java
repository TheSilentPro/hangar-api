package com.github.thesilentpro.hangarapi.model.implementation.project;

public enum Category {

    ADMIN_TOOLS("admin_tools"),
    CHAT("chat"),
    DEV_TOOLS("dev_tools"),
    ECONOMY("economy"),
    GAMEPLAY("gameplay"),
    GAMES("games"),
    PROTECTION("protection"),
    ROLE_PLAYING("role_playing"),
    WORLD_MANAGEMENT("world_management"),
    MISC("misc"),
    UNDEFINED("undefined");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Category fromString(String value) {
        for (Category category : Category.values()) {
            if (category.value.equalsIgnoreCase(value)) {
                return category;
            }
        }
        return UNDEFINED;
    }

}