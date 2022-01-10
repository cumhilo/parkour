package me.cxmilo.parkour.game.handler.impl;

import me.cxmilo.parkour.Parkour;
import me.cxmilo.parkour.game.handler.Handler;
import me.cxmilo.parkour.game.impl.ParkourGame;
import me.cxmilo.parkour.user.User;

public class StartGameHandler
        implements Handler {

    private final User user;
    private final Parkour parkour;

    public StartGameHandler(User user, Parkour parkour) {
        this.user = user;
        this.parkour = parkour;
    }

    @Override
    public void handle() {
        if (canStartGame(user)) {
            startGame(user, parkour);
        }
    }

    private void startGame(User user, Parkour parkour) {
        user.setActiveGame(new ParkourGame(parkour));
    }

    private boolean canStartGame(User user) {
        return user.getActiveGame() == null;
    }
}
