package me.cxmilo.parkour.message;

import java.util.HashMap;
import java.util.Map;

public enum Lang {
    EN("english", "en"),
    ES("español", "es");

    private static final Map<String, Lang> FIND_MAP = new HashMap<>();

    static {
        for (Lang lang : Lang.values()) {
            FIND_MAP.put(lang.abbreviation, lang);
        }
    }

    private final String name;
    private final String abbreviation;

    Lang(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public static Lang find(String abbreviation) {
        return FIND_MAP.get(abbreviation);
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
