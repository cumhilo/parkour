package me.cxmilo.parkour.service;

import me.cxmilo.parkour.Parkour;
import me.cxmilo.parkour.ParkourGameRegistry;
import me.cxmilo.parkour.ParkourPlugin;
import me.cxmilo.parkour.storage.Storage;
import me.cxmilo.parkour.storage.impl.JsonStorage;

import java.util.logging.Level;

public class ParkourService
        implements Service {

    private final Storage<Parkour> storage;
    private final ParkourGameRegistry parkourGameRegistry;

    public ParkourService(ParkourPlugin plugin) {
        this.parkourGameRegistry = plugin.getParkourGameRegistry();
        this.storage = new JsonStorage<>(Parkour.class, plugin);
    }

    @Override
    public void start() {
        // register all files in folder parkour
        parkourGameRegistry.updateParkour(storage.values());
        getLogger().log(Level.INFO, "Successfully loaded '" + storage.values().size() + "' parkours");
    }

    @Override
    public void stop() {
        for (Parkour parkour : parkourGameRegistry.getParkourSet()) {
            storage.save(parkour.getDisplayName(), parkour);
            getLogger().log(Level.INFO, "'" + parkour.getDisplayName() + "' has been successfully saved as a parkour file");
        }
    }
}
