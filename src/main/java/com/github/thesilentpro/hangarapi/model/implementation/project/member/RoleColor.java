package com.github.thesilentpro.hangarapi.model.implementation.project.member;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public enum RoleColor {

    ORCHID("#d946ef"),
    PURPLE("#a855f7"),
    VIOLET("#8b5cf6"),
    BLUE("#6366f1"),
    SKY_BLUE("#3b82f6"),
    CYAN("#0ea5e9"),
    TURQUOISE("#06b6d4"),
    TEAL("#14b8a6"),
    MINT("#34d399"),
    GREEN("#22c55e"),
    OLIVE("#84cc16"),
    YELLOW("#eab308"),
    ORANGE("#f59e0b"),
    PUMPKIN("#f97316"),
    RED("#ef4444"),
    GRAY("#78716c"),
    DARK_GRAY("#a9a9a9"),
    TRANSPARENT(null);

    public static final RoleColor[] VALUES = values();

    private final String code;

    RoleColor(@Nullable String code) {
        this.code = code;
    }

    @NotNull
    public Optional<String> code() {
        return Optional.ofNullable(code);
    }

    @Nullable
    public static RoleColor fromString(String raw) {
        for (RoleColor value : VALUES) {
            if (value.name().equalsIgnoreCase("raw") || (value.code != null && value.code.equalsIgnoreCase(raw))) {
                return value;
            }
        }
        return null;
    }

}