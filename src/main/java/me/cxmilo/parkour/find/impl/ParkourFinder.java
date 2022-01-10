package me.cxmilo.parkour.find.impl;

import me.cxmilo.parkour.Parkour;
import me.cxmilo.parkour.ParkourGameRegistry;
import me.cxmilo.parkour.ParkourPlugin;
import me.cxmilo.parkour.find.Finder;
import me.cxmilo.parkour.location.AbbreviatedLocation;
import org.jetbrains.annotations.Nullable;

public class ParkourFinder
        implements Finder<Parkour, AbbreviatedLocation> {

    private final ParkourGameRegistry gameRegistry;

    public ParkourFinder(ParkourPlugin plugin) {
        this.gameRegistry = plugin.getParkourGameRegistry();
    }

    @Override
    public @Nullable Parkour find(AbbreviatedLocation key) {
        for (Parkour parkour : gameRegistry.getParkourSet()) {
            // check if findable key is equal to the given key
            if (parkour.getFindKey() != null && parkour.getFindKey().isSimilar(key)) {
                return parkour;
            }
        }

        return null;
    }
}
