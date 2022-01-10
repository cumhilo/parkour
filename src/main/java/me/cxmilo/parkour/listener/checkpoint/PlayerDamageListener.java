package me.cxmilo.parkour.listener.checkpoint;

import me.cxmilo.parkour.ParkourPlugin;
import me.cxmilo.parkour.find.Finder;
import me.cxmilo.parkour.find.impl.UserFinder;
import me.cxmilo.parkour.user.User;
import me.cxmilo.parkour.util.AbbreviatedLocation;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.List;

public class PlayerDamageListener
        implements Listener {

    private final MessageHandler messageHandler;
    private final Finder<User, Player> userFinder;

    public PlayerDamageListener(ParkourPlugin plugin) {
        this.messageHandler = plugin.getMessageHandler();
        this.userFinder = new UserFinder(plugin);
    }

    @EventHandler
    public void onReceiveDamage(EntityDamageEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) {
            return;
        }

        User user = userFinder.find((Player) event.getEntity());

        if (user == null || user.getActiveGame() == null) {
            return;
        }

        EntityDamageEvent.DamageCause cause = event.getCause();

        if (cause == EntityDamageEvent.DamageCause.VOID) {
            event.setCancelled(true);

            // teleport to last checkpoint and send a message to player
            List<AbbreviatedLocation> takenCheckpoints = user.getActiveGame().getCheckpointsTaken();

            if (takenCheckpoints.isEmpty()) {
                return;
            }

            user.getPlayer().teleport(takenCheckpoints.get(takenCheckpoints.size() - 1).toLocation());
            messageHandler.send(user, "parkour.checkpoint.teleport");
        } else if (cause == EntityDamageEvent.DamageCause.FALL) {
            event.setCancelled(true);
        }
    }
}
