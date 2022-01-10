package me.cxmilo.parkour;

import me.cxmilo.parkour.location.AbbreviatedLocation;

import java.util.List;

public interface IParkour {

    String getId();

    List<AbbreviatedLocation> getCheckpoints();

    int getMaxTime();

    void setMaxTime(int maxTime);

    int getMaxAttempts();

    void setMaxAttempts(int maxAttempts);
}
