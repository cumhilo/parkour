package me.cxmilo.parkour.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AbbreviatedLocation {

    private final String worldName;

    private final int x;
    private final int y;
    private final int z;

    public AbbreviatedLocation(String worldName, int x, int y, int z) {
        this.worldName = worldName;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static int indexOfLocation(@NotNull List<AbbreviatedLocation> locations, AbbreviatedLocation location) {
        if (location == null) {
            for (int i = 0; i < locations.size(); i++)
                if (locations.get(i) == null)
                    return i;
        } else {
            for (int i = 0; i < locations.size(); i++)
                if (location.isSimilar(locations.get(i)))
                    return i;
        }

        return -1;
    }

    public static boolean containsLocation(List<AbbreviatedLocation> locations, AbbreviatedLocation location) {
        return indexOfLocation(locations, location) >= 0;
    }

    public static AbbreviatedLocation fromLocation(Location location) {
        return new AbbreviatedLocation(location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public boolean isSimilar(@NotNull AbbreviatedLocation location) {
        return x == location.getX() && y == location.getY() && z == location.getZ() && worldName.equals(location.getWorldName());
    }

    public Location toLocation() {
        return new Location(Bukkit.getWorld(worldName), x, y, z);
    }

    public String getWorldName() {
        return worldName;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
