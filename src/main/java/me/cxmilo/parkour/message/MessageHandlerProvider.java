package me.cxmilo.parkour.message;

import me.cxmilo.parkour.ParkourPlugin;
import me.yushust.message.MessageHandler;
import me.yushust.message.MessageProvider;
import me.yushust.message.bukkit.BukkitMessageAdapt;
import me.yushust.message.source.MessageSource;
import me.yushust.message.source.MessageSourceDecorator;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MessageHandlerProvider {

    private static final String DEFAULT_LANGUAGE = ParkourPlugin.getInstance().getConfig().getString("lang");

    private final Plugin plugin;

    public MessageHandlerProvider(Plugin plugin) {
        this.plugin = plugin;
    }

    private MessageSource getMessageSource() {
        return MessageSourceDecorator
                .decorate(BukkitMessageAdapt.newYamlSource(plugin, "lang_%lang%.yml"))
                .addFallbackLanguage(DEFAULT_LANGUAGE)
                .get();
    }

    public MessageHandler get() {
        return MessageHandler.of(
                MessageProvider.create(getMessageSource(), configurationHandle -> {
                    configurationHandle.specify(CommandSender.class)
                            .setLinguist(sender -> DEFAULT_LANGUAGE)
                            .setMessageSender((sender, s, message) -> sender.sendMessage(message));
                    configurationHandle.specify(Player.class)
                            .setLinguist(player -> DEFAULT_LANGUAGE)
                            .setMessageSender((player, s, message) -> player.sendMessage(message));
                    configurationHandle.addInterceptor(s -> ChatColor.translateAlternateColorCodes('&', s));
                })
        );
    }
}
