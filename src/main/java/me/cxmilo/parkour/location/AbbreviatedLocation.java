package me.cxmilo.parkour.location;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

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

    public static AbbreviatedLocation fromLocation(Location location) {
        return new AbbreviatedLocation(location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AbbreviatedLocation)) return false;
        AbbreviatedLocation that = (AbbreviatedLocation) obj;
        return isSimilar(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(worldName, x, y, z);
    }

    public boolean isSimilar(@NotNull AbbreviatedLocation location) {
        return x == location.x && y == location.y && z == location.z && worldName.equals(location.worldName);
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
