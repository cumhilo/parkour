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

    /**
     * This method provides fast access to the plugin
     *
     * @return the plugin
     */
    public static ParkourPlugin getInstance() {
        return JavaPlugin.getPlugin(ParkourPlugin.class);
    }

    @Override
    public void onEnable() {
        // save default config
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        this.parkourGameRegistry = new ParkourGameRegistry();
        this.messageHandler = new MessageHandlerProvider(this).get();

        // initialize all services
        this.parkourService = new ParkourService(this);
        this.commandService = new CommandService(this);
        this.listenerService = new ListenerService(this);

        parkourGameRegistry.initialize();
        startServices();
    }

    @Override
    public void onDisable() {
        stopServices();
    }

    /*
     * Start all services
     */
    private void startServices() {
        commandService.start();
        listenerService.start();
        parkourService.start();
    }

    /*
     * Stop all services
     */
    private void stopServices() {
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
