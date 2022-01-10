package me.cxmilo.parkour.listener.checkpoint;

import me.cxmilo.parkour.Parkour;
import me.cxmilo.parkour.ParkourPlugin;
import me.cxmilo.parkour.find.Finder;
import me.cxmilo.parkour.find.impl.ParkourFinder;
import me.cxmilo.parkour.find.impl.UserFinder;
import me.cxmilo.parkour.game.handler.Handler;
import me.cxmilo.parkour.game.handler.impl.CheckpointUpdateHandler;
import me.cxmilo.parkour.game.handler.impl.StartGameHandler;
import me.cxmilo.parkour.game.impl.ParkourGame;
import me.cxmilo.parkour.location.AbbreviatedLocation;
import me.cxmilo.parkour.user.User;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

public class PlayerCheckpointListener
        implements Listener {

    private final ParkourPlugin plugin;
    private final Finder<Parkour, AbbreviatedLocation> parkourFinder;
    private final Finder<User, Player> userFinder;

    public PlayerCheckpointListener(ParkourPlugin plugin) {
        this.plugin = plugin;
        this.parkourFinder = new ParkourFinder(plugin);
        this.userFinder = new UserFinder(plugin);
    }

    @EventHandler
    public void onInteractWithCheckpoint(PlayerInteractEvent event) {
        // let's check if the player is not on pressure plates
        // if it is not there, we simply return
        if (event.getAction() != Action.PHYSICAL || !isPressurePlate(event.getClickedBlock())) {
            return;
        }

        User user = userFinder.find(event.getPlayer());

        // now let's check if the wrapper of the player (User) is present
        if (user == null) {
            return;
        }

        // try to find a parkour with the location of the pressure plate and if so, start the game
        AbbreviatedLocation pressureLocation = AbbreviatedLocation.fromLocation(event.getClickedBlock().getLocation());
        Parkour parkour = parkourFinder.find(pressureLocation);

        if (parkour != null) {
            // initialize a 'Start game handler' if pressure checkpoint is similar to the '0' checkpoint
            AbbreviatedLocation firstCheckpoint = parkour.getCheckpoints().get(0);
            if (firstCheckpoint != null && firstCheckpoint.isSimilar(pressureLocation)) {
                new StartGameHandler(user, parkour).handle();
            }
        }

        // if the player does not have an active game, the method returns
        // (this would only happen if the plate stepped on was not checkpoint 0
        // and the player was not in a game from before)
        if (user.getActiveGame() == null) {
            return;
        }

        ParkourGame game = user.getActiveGame();
        List<AbbreviatedLocation> gameCheckpoints = game.getParkour().getCheckpoints();

        // we also verify that the pressure plate on which the player is standing
        // is not from the player's active game, if this is the case it will return
        if (!gameCheckpoints.contains(pressureLocation)) {
            return;
        }

        // finally, we let the checkpoint update handler do the rest
        Handler checkpointUpdateHandler = new CheckpointUpdateHandler(plugin, user, gameCheckpoints.indexOf(pressureLocation));
        checkpointUpdateHandler.handle();
    }

    private boolean isPressurePlate(Block block) {
        return block.getType().name().endsWith("PLATE");
    }
}
