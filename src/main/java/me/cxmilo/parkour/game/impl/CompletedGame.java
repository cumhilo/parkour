package me.cxmilo.parkour.game.impl;

import me.cxmilo.parkour.Parkour;
import me.cxmilo.parkour.game.Game;

public class CompletedGame
        implements Game {

    private final Parkour parkour;
    private final long timeTaken;

    public CompletedGame(ParkourGame game) {
        this.parkour = game.getParkour();
        this.timeTaken = game.getTimeElapsed();
    }

    @Override
    public Parkour getParkour() {
        return parkour;
    }

    public long getTimeTaken() {
        return timeTaken;
    }
}
