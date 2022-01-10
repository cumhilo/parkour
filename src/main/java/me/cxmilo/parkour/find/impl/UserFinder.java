package me.cxmilo.parkour.find.impl;

import me.cxmilo.parkour.ParkourPlugin;
import me.cxmilo.parkour.find.Finder;
import me.cxmilo.parkour.user.User;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

public class UserFinder
        implements Finder<User, Player> {

    private final Map<UUID, User> users;

    public UserFinder(ParkourPlugin plugin) {
        this.users = plugin.getParkourGameRegistry().getUsers();
    }

    @Override
    public @Nullable User find(Player key) {
        return users.get(key.getUniqueId());
    }
}
