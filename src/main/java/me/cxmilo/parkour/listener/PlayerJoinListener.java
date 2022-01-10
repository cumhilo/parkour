package me.cxmilo.parkour.listener;

import me.cxmilo.parkour.ParkourPlugin;
import me.cxmilo.parkour.user.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.Map;
import java.util.UUID;

public class PlayerJoinListener
        implements Listener {

    private final Map<UUID, User> users;

    public PlayerJoinListener(ParkourPlugin plugin) {
        this.users = plugin.getParkourGameRegistry().getUsers();
    }

    @EventHandler
    public void onPlayerJoin(AsyncPlayerPreLoginEvent event) {
        users.putIfAbsent(event.getUniqueId(), new User(event.getUniqueId()));
    }
}
