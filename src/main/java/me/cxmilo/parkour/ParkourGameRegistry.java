package me.cxmilo.parkour;

import me.cxmilo.parkour.user.User;

import java.util.*;

public class ParkourGameRegistry {

    private Map<UUID, User> users;
    private Set<Parkour> parkourSet;

    public void updateParkour(Collection<Parkour> parkourCollection) {
        this.parkourSet = new HashSet<>(parkourCollection);
    }

    public void initialize() {
        this.users = new HashMap<>();
        this.parkourSet = new HashSet<>();
    }

    public Map<UUID, User> getUsers() {
        return users;
    }

    public Set<Parkour> getParkourSet() {
        return parkourSet;
    }

}
