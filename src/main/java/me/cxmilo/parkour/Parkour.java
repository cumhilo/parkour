package me.cxmilo.parkour;

import me.cxmilo.parkour.find.Findable;
import me.cxmilo.parkour.location.AbbreviatedLocation;

import java.util.ArrayList;
import java.util.List;

// TODO: this class will be replaced by the "Parkour" interface but not now
public class Parkour
        implements Findable<AbbreviatedLocation> {

    private final List<AbbreviatedLocation> checkpoints;
    private String displayName;
    private int maxTime;

    /**
     * Initialize a new Parkour object
     *
     * @param displayName the name to identify parkour
     * @param maxTime     the maximum time in which the parkour must be completed, if it is 0 or less than 0 no maximum time will be set.
     */
    public Parkour(String displayName, int maxTime) {
        this.displayName = displayName;
        this.maxTime = maxTime;
        this.checkpoints = new ArrayList<>();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public List<AbbreviatedLocation> getCheckpoints() {
        return checkpoints;
    }

    @Override
    public AbbreviatedLocation getFindKey() {
        return checkpoints.isEmpty() ? null : checkpoints.get(0);
    }
}
