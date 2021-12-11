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

    private Map<UUID, ParkourGame> parkourGames;
    private Set<Parkour> parkourSet;
    private Service commandService;
    private MessageHandler messageHandler;

    public static ParkourPlugin getInstance() {
        return JavaPlugin.getPlugin(ParkourPlugin.class);
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.parkourGames = new HashMap<>();
        this.parkourSet = new HashSet<>();
        this.commandService = new CommandService(this);
        this.messageHandler = new MessageHandlerProvider(this).get();

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
