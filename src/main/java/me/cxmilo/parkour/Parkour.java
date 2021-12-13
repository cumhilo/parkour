package me.cxmilo.parkour;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Parkour {

    private final UUID uuid;
    private final Map<Integer, Location> checkpoints;
    private String displayName;

    public Parkour(String displayName) {
        this.displayName = displayName;
        this.uuid = UUID.randomUUID();
        this.checkpoints = new HashMap<>();
    }

    public static Parkour findByName(String name) {
        for (Parkour parkour : ParkourPlugin.getInstance().getParkourGameRegistry().getParkourSet()) {
            if (parkour.getDisplayName().equalsIgnoreCase(name)) {
                return parkour;
            }
        }


        return null;
    }

    public Map<Integer, Location> getCheckpoints() {
        return checkpoints;
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
