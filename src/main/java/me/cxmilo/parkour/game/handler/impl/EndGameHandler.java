package me.cxmilo.parkour.game.handler.impl;

import me.cxmilo.parkour.game.handler.Handler;
import me.cxmilo.parkour.game.impl.CompletedGame;
import me.cxmilo.parkour.game.impl.ParkourGame;
import me.cxmilo.parkour.user.User;

public class EndGameHandler
        implements Handler {

    private final User user;

    public EndGameHandler(User user) {
        this.user = user;
    }

    @Override
    public void handle() {
        endActiveGame(user, getActiveAsCompleted(user));
    }

    private void endActiveGame(User user, CompletedGame parkour) {
        user.getCompletedGames().add(parkour);
        user.setActiveGame(null);
    }

    /**
     * Gets the {@link User user}'s {@link ParkourGame active game} and initialize a new {@link CompletedGame completed game}
     *
     * @param user the user from whom the data is obtained
     * @return a {@link CompletedGame completed game} instance
     */
    private CompletedGame getActiveAsCompleted(User user) {
        return new CompletedGame(user.getActiveGame());
    }
}
