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
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            return true;
        }

        Parkour parkour = Parkour.findByName(args[0]);

        // check if parkour could not be found
        if (parkour == null) {
            plugin.getMessageHandler().sendReplacing(
                    player,
                    "parkour.setup.error",
                    "%name%",
                    args[0]
            );
            return true;
        }

        SetupMode<Player> parkourSetup = new ParkourSetupMode(plugin, parkour);

        if (parkourSetup.hasSetupMode(player)) {
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
        if (args.length != 1) {
            return null;
        }

        List<String> tabCompleter = new ArrayList<>();

        for (Parkour parkour : plugin.getParkourGameRegistry().getParkourSet()) {
            if (args[0].toLowerCase().startsWith(parkour.getDisplayName().toLowerCase())) {
                tabCompleter.add(parkour.getDisplayName());
            }
        }

        return tabCompleter;
    }
}
