package me.cxmilo.parkour.listener;

import me.cxmilo.parkour.Parkour;
import me.cxmilo.parkour.ParkourPlugin;
import me.cxmilo.parkour.game.ParkourCheckpoint;
import me.cxmilo.parkour.setup.ParkourSetupMode;
import me.cxmilo.parkour.setup.SetupMode;
import me.cxmilo.parkour.setup.inventory.SetupInventory;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerParkourSetupListener
        implements Listener {

    private final ParkourPlugin plugin;

    public PlayerParkourSetupListener(ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    // FIXME: This method has many more responsibilities than it should and looks horrible
    //  it was just for testing, it should be changed.
    @EventHandler
    public void onPlayerInteractWithSetupItem(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (!player.hasMetadata(ParkourSetupMode.METADATA)) {
            return;
        }

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Block block = event.getClickedBlock();
        Parkour parkour = Parkour.findByName(player.getMetadata(ParkourSetupMode.METADATA).get(0).asString());

        if (parkour == null) {
            return;
        }

        int nextCheckpoint = parkour.getCheckpoints().size() + 1;

        ParkourCheckpoint checkpoint;
        SetupInventory setupInventory = SetupInventory.getSetupInventory(player.getItemInHand());

        switch (setupInventory) {
            case ADD: {
                checkpoint = new ParkourCheckpoint(parkour, block, nextCheckpoint);
                checkpoint.setLocation(block.getLocation());
                plugin.getMessageHandler().sendReplacing(
                        player,
                        "parkour.checkpoint.add",
                        "%num%",
                        checkpoint.getNum(),
                        "%parkour%",
                        checkpoint.getParkour().getDisplayName()
                );
                break;
            }
            case REMOVE: {
                checkpoint = ParkourCheckpoint.getByBlock(block);

                if (checkpoint != null) {
                    plugin.getMessageHandler().sendReplacing(
                            player,
                            "parkour.checkpoint.remove",
                            "%num%",
                            checkpoint.getNum(),
                            "%parkour%",
                            checkpoint.getParkour().getDisplayName()
                    );
                    checkpoint.remove();
                }
                break;
            }
            case EXIT: {
                SetupMode<Player> playerSetupMode = new ParkourSetupMode(plugin, parkour);
                playerSetupMode.leave(player);
                break;
            }
            default:
                return;
        }

        event.setCancelled(true);
    }
}
