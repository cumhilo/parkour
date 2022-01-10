package me.cxmilo.parkour.command.user;

import me.cxmilo.parkour.ParkourPlugin;
import me.cxmilo.parkour.find.impl.UserFinder;
import me.cxmilo.parkour.message.Lang;
import me.cxmilo.parkour.user.User;
import me.yushust.message.MessageHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class LangCommand
        implements CommandExecutor {

    private final MessageHandler messageHandler;
    private final UserFinder userFinder;

    public LangCommand(ParkourPlugin plugin) {
        this.messageHandler = plugin.getMessageHandler();
        this.userFinder = new UserFinder(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        User user = userFinder.find(player);

        if (args.length < 1) {
            return true;
        }

        Lang lang = Lang.find(args[0].toLowerCase(Locale.ROOT));

        if (lang != null && user != null) {
            user.setLang(lang);
            messageHandler.sendReplacing(user, "user.lang", "%lang%", user.getLang().getName());
        }

        return true;
    }
}
