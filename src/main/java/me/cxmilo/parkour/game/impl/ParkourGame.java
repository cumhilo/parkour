package me.cxmilo.parkour.game.impl;

import me.cxmilo.parkour.Parkour;
import me.cxmilo.parkour.game.Game;
import me.cxmilo.parkour.util.AbbreviatedLocation;

import java.util.Collections;
import java.util.List;

public class ParkourGame
        implements Game {

    private final Parkour parkour;
    private final List<AbbreviatedLocation> checkpointsTaken;
    private long timeElapsed;

    public ParkourGame(Parkour parkour) {
        this.parkour = parkour;
        this.checkpointsTaken = Collections.emptyList();
        this.timeElapsed = 0;
    }

    @Override
    public Parkour getParkour() {
        return parkour;
    }

    public List<AbbreviatedLocation> getCheckpointsTaken() {
        return checkpointsTaken;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }
}
