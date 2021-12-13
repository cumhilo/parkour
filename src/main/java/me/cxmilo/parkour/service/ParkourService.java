package me.cxmilo.parkour.service;

import me.cxmilo.parkour.Parkour;
import me.cxmilo.parkour.ParkourGameRegistry;
import me.cxmilo.parkour.ParkourPlugin;
import me.cxmilo.parkour.storage.Storage;
import me.cxmilo.parkour.storage.impl.JsonStorage;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ParkourService
        implements Service {

    private final ParkourPlugin plugin;
    private final Storage<Parkour> storage;
    private final ParkourGameRegistry parkourGameRegistry;

    public ParkourService(ParkourPlugin plugin) {
        this.plugin = plugin;
        this.parkourGameRegistry = plugin.getParkourGameRegistry();
        this.storage = new JsonStorage<>(Parkour.class, plugin);
    }

    @Override
    public void start() {
        Set<Parkour> parkourSet = new HashSet<>();

        for (File file : Objects.requireNonNull(storage.getFileFolder().listFiles(), "File cannot be null")) {
            parkourSet.add(storage.get(file.getName().replace(".json", "")));
        }

        parkourGameRegistry.updateParkour(parkourSet);
    }

    @Override
    public void stop() {
        for (Parkour parkour : plugin.getParkourGameRegistry().getParkourSet()) {
            storage.save(parkour.getUuid().toString(), parkour);
        }
    }
}
