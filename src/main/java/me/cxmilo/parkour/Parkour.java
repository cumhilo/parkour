package me.cxmilo.parkour;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Parkour {

    private final UUID uuid;
    private final Map<Integer, Location> locationMap;
    private String displayName;

    public Parkour(String displayName) {
        this.displayName = displayName;
        this.uuid = UUID.randomUUID();
        this.locationMap = new HashMap<>();
    }

    public static Parkour findByName(String name) {
        for (Parkour parkour : JavaPlugin.getPlugin(ParkourPlugin.class).getParkourSet()) {
            if (parkour.getDisplayName().equalsIgnoreCase(name)) {
                return parkour;
            }
        }

        return null;
    }

    public Map<Integer, Location> getLocationMap() {
        return locationMap;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
