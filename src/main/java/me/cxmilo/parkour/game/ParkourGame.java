package me.cxmilo.parkour.game;

import me.cxmilo.parkour.Parkour;
import me.cxmilo.parkour.ParkourPlugin;
import me.cxmilo.parkour.util.TimeUtil;
import org.bukkit.entity.Player;

public class ParkourGame {

    private final Parkour parkour;
    private long startTime;
    private long endTime;
    private int lastCheckPoint;

    public ParkourGame(Parkour parkour) {
        this.parkour = parkour;
        this.startTime = System.currentTimeMillis();
        this.endTime = 0;
        this.lastCheckPoint = 0;
    }

    public static ParkourGame getByPlayer(Player player) {
        return ParkourPlugin.getInstance().getParkourGameRegistry().getParkourGames().get(player.getUniqueId());
    }

    public Parkour getParkour() {
        return parkour;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getDisplayTime() {
        return TimeUtil.durationToHumanTime(System.currentTimeMillis() - startTime);
    }

    public int getLastCheckPoint() {
        return lastCheckPoint;
    }

    public void setLastCheckPoint(int lastCheckPoint) {
        this.lastCheckPoint = lastCheckPoint;
    }
}
