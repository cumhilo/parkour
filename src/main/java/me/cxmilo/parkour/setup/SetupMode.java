package me.cxmilo.parkour.setup;

import org.bukkit.entity.Player;

public interface SetupMode {

    void enter(Player player);

    void leave(Player player);

    boolean isSetupMode(Player player);
}
