package me.cxmilo.parkour.service.bukkit;

import me.cxmilo.parkour.ParkourPlugin;
import me.cxmilo.parkour.command.ParkourCreateCommand;
import me.cxmilo.parkour.command.ParkourSetupCommand;
import me.cxmilo.parkour.command.user.LangCommand;
import me.cxmilo.parkour.service.Service;
import me.yushust.message.util.Validate;
import org.bukkit.command.CommandExecutor;

import java.util.Objects;

public class CommandService
        implements Service {

    private final ParkourPlugin plugin;

    public CommandService(ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    // I took the idea of replacements from the nmessage lib
    // created by yusshu and turned it into a "setup-command" method
    // credits: https://github.com/yusshu/nmessage
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

            Objects.requireNonNull(plugin.getCommand(name.toString()), "'" + name + "' command not found'")
                    .setExecutor((CommandExecutor) commandExecutor);
        }
    }

    @Override
    public void start() {
        ParkourSetupCommand setupCommand = new ParkourSetupCommand(plugin);

        // TODO: remove this, it is unnecessary, there are very few commands
        registerCommands(
                "lang",
                new LangCommand(plugin),
                "setup",
                setupCommand,
                "parkour",
                new ParkourCreateCommand(plugin)
        );

        plugin.getCommand("setup").setTabCompleter(setupCommand);
    }
}
