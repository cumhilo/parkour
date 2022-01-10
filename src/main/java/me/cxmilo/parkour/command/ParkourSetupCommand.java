package me.cxmilo.parkour.command;

import me.cxmilo.parkour.Parkour;
import me.cxmilo.parkour.ParkourPlugin;
import me.cxmilo.parkour.find.Finder;
import me.cxmilo.parkour.find.impl.UserFinder;
import me.cxmilo.parkour.setup.ParkourSetupMode;
import me.cxmilo.parkour.setup.SetupMode;
import me.cxmilo.parkour.user.User;
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
    private final Finder<User, Player> userFinder;

    public ParkourSetupCommand(ParkourPlugin plugin) {
        this.plugin = plugin;
        this.userFinder = new UserFinder(plugin);
    }

    private Parkour findParkour(String name) {
        for (Parkour parkour : plugin.getParkourGameRegistry().getParkourSet()) {
            if (parkour.getDisplayName().equalsIgnoreCase(name)) {
                return parkour;
            }
        }

        return null;
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

        if (args.length < 1) {
            return true;
        }

        User user = userFinder.find(player);

        String name = String.join(" ", args).trim();
        Parkour parkour = findParkour(name);

        // check if parkour could not be found
        if (parkour == null) {
            plugin.getMessageHandler().sendReplacing(user, "parkour.setup.error", "%parkour%", name);
            return true;
        }

        SetupMode parkourSetup = new ParkourSetupMode(plugin, parkour);

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
        if (args.length < 1) {
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
