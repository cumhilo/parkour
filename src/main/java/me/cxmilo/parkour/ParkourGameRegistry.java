package me.cxmilo.parkour;

import me.cxmilo.parkour.game.ParkourGame;

import java.util.*;

public class ParkourGameRegistry {

    private Map<UUID, ParkourGame> parkourGames;
    private Set<Parkour> parkourSet;

    public void updateParkour(Collection<Parkour> parkourCollection) {
        parkourSet = new HashSet<>(parkourCollection);
    }

    public void initialize() {
        this.parkourGames = new HashMap<>();
        this.parkourSet = new HashSet<>();
    }

    public Map<UUID, ParkourGame> getParkourGames() {
        return parkourGames;
    }

    public Set<Parkour> getParkourSet() {
        return parkourSet;
    }
}
