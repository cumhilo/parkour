package me.cxmilo.parkour.setup;

import me.cxmilo.parkour.Parkour;
import me.cxmilo.parkour.setup.inventory.SetupItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class ParkourSetupMode
        implements SetupMode {

    public static final String METADATA = "parkour-setup";

    private final Plugin plugin;
    private final Parkour parkour;
    private final Map<Player, ItemStack[]> inventory;

    public ParkourSetupMode(Plugin plugin, Parkour parkour) {
        this.plugin = plugin;
        this.parkour = parkour;
        this.inventory = new HashMap<>();
    }

    @Override
    public void enter(Player player) {
        setupInventory(player);
        player.setMetadata(METADATA, new FixedMetadataValue(plugin, parkour));
    }

    @Override
    public void leave(Player player) {
        player.removeMetadata(METADATA, plugin);
        player.getInventory().setContents(inventory.get(player));
    }

    @Override
    public boolean isSetupMode(Player player) {
        return player.hasMetadata(METADATA);
    }

    private void setupInventory(Player player) {
        onSaveAndClearInventory(player, player.getInventory());

        setItemInInventory(player, 0, SetupItem.ADD);
        setItemInInventory(player, 1, SetupItem.REMOVE);
        setItemInInventory(player, 7, SetupItem.SAVE);
        setItemInInventory(player, 8, SetupItem.EXIT);
    }

    private void setItemInInventory(Player player, int index, SetupItem setupItem) {
        setItemInInventory(player.getInventory(), index, setupItem.getItemStack());
    }

    private void setItemInInventory(Inventory inv, int index, ItemStack itemStack) {
        inv.setItem(index, itemStack);
    }

    private void onSaveAndClearInventory(Player player, Inventory toSave) {
        inventory.put(player, toSave.getContents());
        toSave.clear();
    }
}
