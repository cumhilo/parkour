package me.cxmilo.parkour.listener;

import me.cxmilo.parkour.ParkourPlugin;
import me.cxmilo.parkour.game.ParkourCheckPoint;
import me.cxmilo.parkour.game.ParkourGame;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerCheckPointListener
        implements Listener {

    private final ParkourPlugin plugin;

    public PlayerCheckPointListener(ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onReceiveVoidDamage(EntityDamageEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) {
            return;
        }

        Player player = (Player) event.getEntity();
        ParkourGame game = ParkourGame.getByPlayer(player);

        if (game == null) {
            return;
        }

        if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
            event.setCancelled(true);

            // teleport to last checkpoint and send a message to player
            player.teleport(game.getParkour().getLocationMap().get(game.getLastCheckPoint()));
            plugin.getMessageHandler().sendReplacing(
                    player,
                    "parkour.checkpoint.teleport",
                    "%player%",
                    player.getDisplayName(),
                    "%parkour%",
                    game.getParkour().getDisplayName(),
                    "%checkpoint%",
                    game.getLastCheckPoint()
            );
        }
    }

    @EventHandler
    public void onTouchCheckPoint(PlayerMoveEvent event) {
        Block blockTo = event.getTo().getBlock();

        ParkourCheckPoint checkPoint = ParkourCheckPoint.getByBlock(blockTo);

        if (checkPoint == null) {
            return;
        }

        Player player = event.getPlayer();
        ParkourGame game = ParkourGame.getByPlayer(player);

        if (game == null) {
            // if the player is not in a parkour and touches checkpoint 0 of a parkour the game will start
            if (checkPoint.getNum() == 0) {
                plugin.getParkourGames().put(player.getUniqueId(), new ParkourGame(checkPoint.getParkour()));
            }
        } else {
            // if the control point touched is less than or equal to the last control point it only returns
            if (checkPoint.getNum() <= game.getLastCheckPoint()) {
                return;
            }

            plugin.getMessageHandler().sendReplacing(
                    player,
                    "parkour.checkpoint.get",
                    "%num%",
                    checkPoint.getNum(),
                    "%parkour%",
                    game.getParkour().getDisplayName(),
                    "%time%",
                    game.getDisplayTime());
            game.setLastCheckPoint(checkPoint.getNum());
        }
    }
}
