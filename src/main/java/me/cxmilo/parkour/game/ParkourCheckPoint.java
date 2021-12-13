package me.cxmilo.parkour.game;

import me.cxmilo.parkour.Parkour;
import me.cxmilo.parkour.ParkourPlugin;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

public class ParkourCheckpoint {

    private final Parkour parkour;
    private Block block;
    private int num;

    public ParkourCheckpoint(Parkour parkour, Block block, int num) {
        this.parkour = parkour;
        this.block = block;
        this.num = num;
    }

    public static ParkourCheckpoint getByBlock(Block block) {
        if (!block.hasMetadata("parkour") || !block.hasMetadata("point")) {
            return null;
        }

        Parkour parkour = Parkour.findByName(block.getMetadata("parkour").get(0).asString());

        if (parkour == null) {
            return null;
        }

        return new ParkourCheckpoint(parkour, block, block.getMetadata("point").get(0).asInt());
    }

    public void remove() {
        parkour.getCheckpoints().remove(num);

        block.removeMetadata("parkour", ParkourPlugin.getInstance());
        block.removeMetadata("point", ParkourPlugin.getInstance());
    }

    public void setLocation(Location location) {
        setLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ(), location.getWorld());
    }

    private void setLocation(int x, int y, int z, World world) {
        parkour.getCheckpoints().put(num, new Location(world, x, y, z));

        setBlockData("parkour", parkour.getDisplayName());
        setBlockData("point", num);
    }

    private void setBlockData(String data, Object value) {
        block.setMetadata(data, new FixedMetadataValue(JavaPlugin.getPlugin(ParkourPlugin.class), value));
    }

    public Parkour getParkour() {
        return parkour;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
