package me.cxmilo.parkour.parkour;

import me.cxmilo.parkour.IParkour;
import me.cxmilo.parkour.util.AbbreviatedLocation;

import java.util.List;

public abstract class AbstractParkour
        implements IParkour {

    private final String id;
    private final List<AbbreviatedLocation> checkpoints;
    private int maxTime;
    private int maxAttempts;

    protected AbstractParkour(String id, List<AbbreviatedLocation> checkpoints, int maxTime, int maxAttempts) {
        this.id = id;
        this.checkpoints = checkpoints;
        this.maxTime = maxTime;
        this.maxAttempts = maxAttempts;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public List<AbbreviatedLocation> getCheckpoints() {
        return checkpoints;
    }

    @Override
    public int getMaxTime() {
        return maxTime;
    }

    @Override
    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    @Override
    public int getMaxAttempts() {
        return maxAttempts;
    }

    @Override
    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public abstract void start();
}
