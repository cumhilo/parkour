package me.cxmilo.parkour.command;

import me.cxmilo.parkour.Parkour;
import me.cxmilo.parkour.ParkourPlugin;
import me.cxmilo.parkour.setup.ParkourSetupMode;
import me.cxmilo.parkour.setup.SetupMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ParkourSetupCommand
        implements CommandExecutor, TabExecutor {

    private final ParkourPlugin plugin;

    public ParkourSetupCommand(ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {
        // command can only be executed by players!
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        // arguments are invalid
        if (args.length != 2) {
            return true;
        }

        Parkour parkour = Parkour.findByName(args[1]);

        // check if parkour could not be found
        if (parkour == null) {
            throw new IllegalArgumentException(args[1] + " is not a valid parkour!");
        }

        SetupMode parkourSetup = new ParkourSetupMode(plugin, parkour);

        if (parkourSetup.isSetupMode(player)) {
            parkourSetup.leave(player);
        } else {
            parkourSetup.enter(player);
        }

        return true;
    }


    @Override
    public List<String> onTabComplete(
            CommandSender sender,
            Command command,
            String alias,
            String[] args
    ) {
        if (args.length != 2) {
            return null;
        }

        List<String> tabCompleter = new ArrayList<>();

        for (Parkour parkour : plugin.getParkourSet()) {
            if (args[1].toLowerCase().startsWith(parkour.getDisplayName().toLowerCase())) {
                tabCompleter.add(parkour.getDisplayName());
            }
        }

        return tabCompleter;
    }
}
