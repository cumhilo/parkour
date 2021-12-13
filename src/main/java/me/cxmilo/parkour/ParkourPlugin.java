package me.cxmilo.parkour;

import me.cxmilo.parkour.message.MessageHandlerProvider;
import me.cxmilo.parkour.service.ParkourService;
import me.cxmilo.parkour.service.Service;
import me.cxmilo.parkour.service.bukkit.CommandService;
import me.cxmilo.parkour.service.bukkit.ListenerService;
import me.yushust.message.MessageHandler;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Parkour plugin main class
 */
public class ParkourPlugin
        extends JavaPlugin {

    private ParkourGameRegistry parkourGameRegistry;
    private Service parkourService;
    private Service commandService;
    private Service listenerService;
    private MessageHandler messageHandler;

    public static ParkourPlugin getInstance() {
        return JavaPlugin.getPlugin(ParkourPlugin.class);
    }

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        this.parkourGameRegistry = new ParkourGameRegistry();
        this.parkourService = new ParkourService(this);
        this.commandService = new CommandService(this);
        this.listenerService = new ListenerService(this);
        this.messageHandler = new MessageHandlerProvider(this).get();

        parkourGameRegistry.initialize();
        parkourService.start();
        commandService.start();
        listenerService.start();
    }

    @Override
    public void onDisable() {
        parkourService.stop();
        commandService.stop();
        listenerService.stop();
    }

    public ParkourGameRegistry getParkourGameRegistry() {
        return parkourGameRegistry;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }
}
