package me.cxmilo.parkour.command;

import me.cxmilo.parkour.Parkour;
import me.cxmilo.parkour.ParkourPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ParkourCreateCommand
        implements CommandExecutor {

    private final ParkourPlugin plugin;

    public ParkourCreateCommand(ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    private boolean isValidName(String name) {
        for (char c : name.toCharArray()) {
            if (Character.isDigit(c) || Character.isAlphabetic(c)) {
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

        if (args.length != 3 || !args[1].equalsIgnoreCase("create")) {
            return true;
        }

        String parkourDisplayName = args[2];

        // check if name is invalid using the method isValidName
        if (!isValidName(parkourDisplayName)) {
            plugin.getMessageHandler().sendReplacing(
                    player,
                    "parkour.invalid-name",
                    "%name%",
                    parkourDisplayName
            );
            return true;
        }

        Parkour parkour = new Parkour(parkourDisplayName);

        plugin.getParkourSet().add(parkour);
        plugin.getMessageHandler().sendReplacing(
                player,
                "parkour.create",
                "%name%",
                parkour.getDisplayName(),
                "%uuid%",
                parkour.getUuid().toString());
        return true;
    }
}
