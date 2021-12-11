package me.cxmilo.parkour.service;

import java.util.logging.Level;
import java.util.logging.Logger;

public interface Service {

    default void start() {
        getLogger().log(Level.INFO, "{} has been initialized", getClass().getSimpleName());
    }

    default void stop() {
        getLogger().log(Level.INFO, "{} has been stopped", getClass().getSimpleName());
    }

    Logger getLogger();
}
