package me.cxmilo.parkour.setup.inventory;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.core.item.type.ItemBuilder;

public enum SetupItem {
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
    SAVE(ItemBuilder
            .newBuilder(Material.CHEST)
            .build()),
    EXIT(null);

    private final ItemStack itemStack;

    SetupItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
