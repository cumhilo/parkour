package me.cxmilo.parkour.listener;

import me.cxmilo.parkour.setup.ParkourSetupMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerParkourSetupListener
        implements Listener {

    @EventHandler
    public void onPlayerInteractWithSetupItem(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (!player.hasMetadata(ParkourSetupMode.METADATA)) {
            return;
        }
    }
}
