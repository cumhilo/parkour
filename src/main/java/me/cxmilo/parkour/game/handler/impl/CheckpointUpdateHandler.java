package me.cxmilo.parkour.game.handler.impl;

import me.cxmilo.parkour.ParkourPlugin;
import me.cxmilo.parkour.game.handler.Handler;
import me.cxmilo.parkour.game.impl.ParkourGame;
import me.cxmilo.parkour.location.AbbreviatedLocation;
import me.cxmilo.parkour.user.User;
import me.yushust.message.MessageHandler;

import java.util.List;

public class CheckpointUpdateHandler
        implements Handler {

    private final User user;
    private final MessageHandler messageHandler;
    private final ParkourGame game;
    private final List<AbbreviatedLocation> checkpoints;
    private final Handler endGameHandler;
    private final int checkpointIndex;

    public CheckpointUpdateHandler(ParkourPlugin plugin, User user, int checkpointIndex) {
        this.game = user.getActiveGame();
        this.checkpoints = game.getCheckpointsTaken();
        this.checkpointIndex = checkpointIndex;
        this.endGameHandler = new EndGameHandler(user);
        this.messageHandler = plugin.getMessageHandler();
        this.user = user;
    }

    @Override
    public void handle() {
        if (canUpdate(checkpointIndex)) {
            update(getCheckpointLocation(checkpointIndex));
            messageHandler.send(user, "parkour.checkpoint.get");
        } else if (isLastCheckpoint()) {
            endGameHandler.handle();
            messageHandler.send(user, "parkour.game.end");
        }
    }

    private AbbreviatedLocation getCheckpointLocation(int index) {
        return game.getParkour().getCheckpoints().get(index);
    }

    private void update(AbbreviatedLocation location) {
        checkpoints.add(location);
    }

    private boolean canUpdate(int index) {
        return checkpoints.size() >= index && checkpoints.size() < game.getParkour().getCheckpoints().size();
    }

    private boolean isLastCheckpoint() {
        return checkpoints.size() == game.getParkour().getCheckpoints().size();
    }
}
