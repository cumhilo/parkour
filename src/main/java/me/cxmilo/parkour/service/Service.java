package me.cxmilo.parkour.service;

import me.cxmilo.parkour.ParkourPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public interface Service {

    default void start() {
        getLogger().log(Level.INFO, getClass().getSimpleName() + " has been initialized");
    }

    default void stop() {
        getLogger().log(Level.INFO, getClass().getSimpleName() + " has been stopped");
    }

    default Logger getLogger() {
        return ParkourPlugin.getInstance().getLogger();
    }
}
