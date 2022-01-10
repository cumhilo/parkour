package me.cxmilo.parkour.setup;

import me.cxmilo.parkour.Parkour;
import me.cxmilo.parkour.setup.inventory.SetupInventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

public class ParkourSetupMode
        implements SetupMode {

    public static final String METADATA = "parkour-setup";

    private final Plugin plugin;
    private final Parkour parkour;

    public ParkourSetupMode(Plugin plugin, Parkour parkour) {
        this.plugin = plugin;
        this.parkour = parkour;
    }

    @Override
    public void enter(Player player) {
        setupInventory(player);
        player.setMetadata(METADATA, new FixedMetadataValue(plugin, parkour.getDisplayName()));
    }

    @Override
    public void leave(Player player) {
        player.getInventory().clear();
        player.updateInventory();
        player.removeMetadata(METADATA, plugin);
    }

    @Override
    public boolean hasSetupMode(Player player) {
        return player.hasMetadata(METADATA);
    }

    private void setupInventory(Player player) {
        player.getInventory().clear();

        setItemInInventory(player, 0, SetupInventory.ADD);
        setItemInInventory(player, 1, SetupInventory.REMOVE);
        setItemInInventory(player, 8, SetupInventory.EXIT);
    }

    private void setItemInInventory(Player player, int index, SetupInventory setupInventory) {
        setItemInInventory(player.getInventory(), index, setupInventory.getItemStack());
    }

    private void setItemInInventory(Inventory inv, int index, ItemStack itemStack) {
        inv.setItem(index, itemStack);
    }
}
