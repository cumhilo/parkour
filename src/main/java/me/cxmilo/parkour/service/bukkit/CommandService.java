package me.cxmilo.parkour.service.bukkit;

import me.cxmilo.parkour.ParkourPlugin;
import me.cxmilo.parkour.command.ParkourCreateCommand;
import me.cxmilo.parkour.command.ParkourSetupCommand;
import me.cxmilo.parkour.service.Service;
import me.yushust.message.util.Validate;
import org.bukkit.command.CommandExecutor;

import java.util.logging.Logger;

public class CommandService
        implements Service {

    private final ParkourPlugin plugin;

    public CommandService(ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    private void registerCommands(Object... commands) {
        Validate.isTrue(commands.length % 2 == 0, "Commands array must be pair!");

        for (int i = 0; i < commands.length; i++) {
            // check if odd objects are Strings
            Object name = commands[i];
            Validate.isTrue(name instanceof String, "The element at index " + i + " found in the command array must be a string!");

            // 1 is added to i so that it can get the even objects from the array
            i++;

            // check if the peer objects are CommandExecutors
            Object commandExecutor = commands[i];
            Validate.isTrue(commandExecutor instanceof CommandExecutor, "The element at index " + i + " found in the command array must be a command executor!");

            plugin.getCommand(name.toString()).setExecutor((CommandExecutor) commandExecutor);
        }
    }

    @Override
    public void start() {
        ParkourSetupCommand setupCommand = new ParkourSetupCommand(plugin);

        registerCommands(
                "setup",
                setupCommand,
                "parkour",
                new ParkourCreateCommand(plugin)
        );

        plugin.getCommand("setup").setTabCompleter(setupCommand);
    }

    @Override
    public Logger getLogger() {
        return plugin.getLogger();
    }
}
