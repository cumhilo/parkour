package me.cxmilo.parkour.setup.inventory;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.core.item.type.ItemBuilder;

import java.util.HashMap;
import java.util.Map;

// FIXME: remove this class, it looks a bit ugly and I think it can be improved
public enum SetupInventory {
    ADD(ItemBuilder
            .newDyeItemBuilder(Material.INK_SACK.name(), DyeColor.GREEN)
            .setName("§aCheckpoint adder")
            .setLore("", "Add a checkpoint to the position you right-click with this item", "")
            .build()),
    REMOVE(ItemBuilder
            .newDyeItemBuilder(Material.INK_SACK.name(), DyeColor.RED)
            .setName("§aCheckpoint remover")
            .setLore("", "Removes a checkpoint at the position you right-click with this item", "")
            .build()),
    EXIT(ItemBuilder
            .newBuilder(Material.ARROW)
            .setName("§aExit")
            .setLore("", "Exit setup mode", "")
            .build());

    private static final Map<ItemStack, SetupInventory> SETUP_INVENTORY_MAP = new HashMap<>();
    private final ItemStack itemStack;

    static {
        for (SetupInventory setupInventory : values()) {
            SETUP_INVENTORY_MAP.put(setupInventory.getItemStack(), setupInventory);
        }
    }

    SetupInventory(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public static SetupInventory getSetupInventory(ItemStack itemStack) {
        return SETUP_INVENTORY_MAP.get(itemStack);
    }
}
