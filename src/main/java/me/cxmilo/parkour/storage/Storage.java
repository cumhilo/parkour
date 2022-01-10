package me.cxmilo.parkour.storage;

import java.util.Collection;
import java.util.Optional;

public interface Storage<O> {

    void save(String identifier, O object);

    default void saveIfNoExits(String identifier, O object) {
        if (get(identifier) != null) {
            return;
        }

        save(identifier, object);
    }

    O get(String identifier);

    default Optional<O> find(String identifier) {
        return Optional.ofNullable(get(identifier));
    }

    default O getOrDefault(String identifier, O def) {
        if (get(identifier) != null) {
            return get(identifier);
        }

        return def;
    }

    Collection<O> values();
}
