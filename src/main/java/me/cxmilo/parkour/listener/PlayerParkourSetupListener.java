package me.cxmilo.parkour.listener;

import me.cxmilo.parkour.Parkour;
import me.cxmilo.parkour.ParkourPlugin;
import me.cxmilo.parkour.find.Finder;
import me.cxmilo.parkour.find.impl.UserFinder;
import me.cxmilo.parkour.setup.ParkourSetupMode;
import me.cxmilo.parkour.setup.SetupMode;
import me.cxmilo.parkour.setup.inventory.SetupInventory;
import me.cxmilo.parkour.user.User;
import me.cxmilo.parkour.util.AbbreviatedLocation;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerParkourSetupListener
        implements Listener {

    private final ParkourPlugin plugin;
    private final Finder<User, Player> userFinder;

    public PlayerParkourSetupListener(ParkourPlugin plugin) {
        this.plugin = plugin;
        this.userFinder = new UserFinder(plugin);
    }

    private Parkour findParkour(String name) {
        for (Parkour parkour : plugin.getParkourGameRegistry().getParkourSet()) {
            if (parkour.getDisplayName().equalsIgnoreCase(name)) {
                return parkour;
            }
        }

        return null;
    }

    // FIXME: This method has many more responsibilities than it should and looks horrible
    //  it was just for testing, it should be changed.
    @EventHandler
    public void onPlayerInteractWithSetupItem(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (!player.hasMetadata(ParkourSetupMode.METADATA) || event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        User user = userFinder.find(player);

        Parkour parkour = findParkour(player.getMetadata(ParkourSetupMode.METADATA).get(0).asString());
        SetupInventory setupInventory = SetupInventory.getSetupInventory(player.getItemInHand());

        if (user == null || parkour == null || setupInventory == null) {
            return;
        }

        AbbreviatedLocation checkpoint = AbbreviatedLocation.fromLocation(event.getClickedBlock().getLocation());

        switch (setupInventory) {
            case ADD: {
                if (AbbreviatedLocation.containsLocation(parkour.getCheckpoints(), checkpoint)) {
                    player.sendMessage("Checkpoint already exists!");
                    return;
                }

                if (!isPlate(checkpoint.toLocation().getBlock())) {
                    player.sendMessage("Block must be a pressure plate");
                    return;
                }

                parkour.getCheckpoints().add(checkpoint);
                plugin.getMessageHandler().send(user, "parkour.checkpoint.add");
                break;
            }
            case REMOVE: {
                plugin.getMessageHandler().send(user, "parkour.checkpoint.remove");
                parkour.getCheckpoints().remove(checkpoint);
                break;
            }
            case EXIT: {
                SetupMode playerSetupMode = new ParkourSetupMode(plugin, parkour);
                playerSetupMode.leave(player);
                break;
            }
        }

        event.setCancelled(true);
    }

    private boolean isPlate(Block block) {
        return block.getType().name().endsWith("PLATE");
    }
}
