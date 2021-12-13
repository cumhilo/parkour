package me.cxmilo.parkour.setup;

import org.bukkit.entity.Entity;

// I think this abstraction is unnecessary, I will probably change it
// just in case I want to do something bigger in the future.
public interface SetupMode<T extends Entity> {

    void enter(T entity);

    void leave(T entity);

    boolean hasSetupMode(T entity);
}
