package me.cxmilo.parkour.game;

import org.bukkit.entity.Player;

public class CheckpointGameHandler {

    // FIXME: this method does more than what it says, you should rename it
    //  and create more methods for each of its functions
    public boolean updateLastGameCheckpoint(ParkourGame game, ParkourCheckpoint checkpoint) {
        if (checkpoint.getNum() <= game.getLastCheckPoint()) {
            return false;
        }

        game.setLastCheckPoint(checkpoint.getNum());
        return true;
    }

    public void teleportToLastCheckpoint(Player player, ParkourGame game) {
        player.teleport(game.getParkour().getCheckpoints().get(game.getLastCheckPoint()));
    }
}
