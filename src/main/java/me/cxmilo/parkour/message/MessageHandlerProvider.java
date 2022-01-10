package me.cxmilo.parkour.message;

import me.cxmilo.parkour.ParkourPlugin;
import me.cxmilo.parkour.user.User;
import me.yushust.message.MessageHandler;
import me.yushust.message.MessageProvider;
import me.yushust.message.bukkit.BukkitMessageAdapt;
import me.yushust.message.source.MessageSource;
import me.yushust.message.source.MessageSourceDecorator;
import org.bukkit.ChatColor;

public class MessageHandlerProvider {

    private final ParkourPlugin plugin;

    public MessageHandlerProvider(ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    private MessageSource getMessageSource() {
        return MessageSourceDecorator
                .decorate(BukkitMessageAdapt.newYamlSource(plugin, "lang_%lang%.yml"))
                .addFallbackLanguage("en")
                .get();
    }

    public MessageHandler get() {
        return MessageHandler.of(
                MessageProvider.create(getMessageSource(), configurationHandle -> {
                    configurationHandle.specify(User.class)
                            .setLinguist(user -> user.getLang().getAbbreviation())
                            .setMessageSender((user, mode, message) -> user.getPlayer().sendMessage(message));
                    configurationHandle.addInterceptor(s -> ChatColor.translateAlternateColorCodes('&', s));
                })
        );
    }
}
