package me.cxmilo.parkour;

import me.cxmilo.parkour.game.ParkourGame;
import me.cxmilo.parkour.message.MessageHandlerProvider;
import me.cxmilo.parkour.service.Service;
import me.cxmilo.parkour.service.bukkit.CommandService;
import me.yushust.message.MessageHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;


/**
 * Parkour plugin main class
 */
public class ParkourPlugin
        extends JavaPlugin {

    private final Map<UUID, ParkourGame> parkourGames = new HashMap<>();
    private final Set<Parkour> parkourSet = new HashSet<>();
    private final Service commandService = new CommandService(this);
    private final MessageHandler messageHandler = new MessageHandlerProvider(this).get();

    public static ParkourPlugin getInstance() {
        return JavaPlugin.getPlugin(ParkourPlugin.class);
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        commandService.start();
    }

    @Override
    public void onDisable() {
        commandService.stop();
    }

    public Map<UUID, ParkourGame> getParkourGames() {
        return parkourGames;
    }

    public Set<Parkour> getParkourSet() {
        return parkourSet;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }
}
