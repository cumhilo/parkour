package me.cxmilo.parkour.command;

import me.cxmilo.parkour.Parkour;
import me.cxmilo.parkour.ParkourPlugin;
import me.cxmilo.parkour.find.Finder;
import me.cxmilo.parkour.find.impl.UserFinder;
import me.cxmilo.parkour.user.User;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ParkourCreateCommand
        implements CommandExecutor {

    private final ParkourPlugin plugin;
    private final Finder<User, Player> userFinder;

    public ParkourCreateCommand(ParkourPlugin plugin) {
        this.plugin = plugin;
        this.userFinder = new UserFinder(plugin);
    }

    private boolean isValidName(String name) {
        for (char c : name.toCharArray()) {
            if (Character.isDigit(c) || Character.isAlphabetic(c) || c == ' ') {
                continue;
            }

            return false;
        }

        return true;
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

        if (args.length < 2 || !args[0].equalsIgnoreCase("create")) {
            return true;
        }

        User user = userFinder.find(player);

        String parkourDisplayName = String.join(" ", Arrays.copyOfRange(args, 1, args.length)).trim();

        // check if name is invalid using the method isValidName
        if (!isValidName(parkourDisplayName)) {
            plugin.getMessageHandler().sendReplacing(
                    user,
                    "parkour.invalid-name",
                    "%name%",
                    parkourDisplayName
            );
            return true;
        }

        Parkour parkour = new Parkour(parkourDisplayName, 0);

        plugin.getParkourGameRegistry().getParkourSet().add(parkour);
        plugin.getMessageHandler().send(user, "parkour.create");
        return true;
    }
}
