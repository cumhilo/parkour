package me.cxmilo.parkour.service;

import java.util.logging.Level;
import java.util.logging.Logger;

public interface Service {

    default void start() {
        getLogger().log(Level.INFO, getClass().getSimpleName() + " has been initialized");
    }

    default void stop() {
        getLogger().log(Level.INFO, getClass().getSimpleName() + " has been stopped");
    }

    Logger getLogger();
}
