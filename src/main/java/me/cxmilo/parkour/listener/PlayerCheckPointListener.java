package me.cxmilo.parkour.listener;

import me.cxmilo.parkour.ParkourPlugin;
import me.cxmilo.parkour.game.CheckpointGameHandler;
import me.cxmilo.parkour.game.ParkourCheckpoint;
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
    private final CheckpointGameHandler checkpointGameHandler;

    public PlayerCheckPointListener(ParkourPlugin plugin) {
        this.plugin = plugin;
        this.checkpointGameHandler = new CheckpointGameHandler();
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
            checkpointGameHandler.teleportToLastCheckpoint(player, game);
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
        Block blockFrom = event.getFrom().getBlock();

        if (blockTo.getLocation().equals(blockFrom.getLocation())) {
            return;
        }

        ParkourCheckpoint checkpoint = ParkourCheckpoint.getByBlock(blockTo);

        if (checkpoint == null) {
            return;
        }

        Player player = event.getPlayer();
        ParkourGame game = ParkourGame.getByPlayer(player);

        if (game == null) {
            // if the player is not in a parkour and touches checkpoint 0 of a parkour the game will start
            if (checkpoint.getNum() == 1) {
                plugin.getParkourGameRegistry().getParkourGames().put(player.getUniqueId(), new ParkourGame(checkpoint.getParkour()));
                plugin.getMessageHandler().sendReplacing(
                        player,
                        "parkour.start",
                        "%parkour%",
                        checkpoint.getParkour().getDisplayName()
                );
            }
        } else {
            // update the last checkpoint and send a message to the player
            if (checkpointGameHandler.updateLastGameCheckpoint(game, checkpoint)) {
                plugin.getMessageHandler().sendReplacing(
                        player,
                        "parkour.checkpoint.get",
                        "%num%",
                        checkpoint.getNum(),
                        "%parkour%",
                        game.getParkour().getDisplayName(),
                        "%time%",
                        game.getDisplayTime());
            }
        }
    }
}
