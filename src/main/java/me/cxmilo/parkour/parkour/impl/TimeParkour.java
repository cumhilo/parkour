package me.cxmilo.parkour.parkour.impl;

import me.cxmilo.parkour.parkour.AbstractParkour;
import me.yushust.message.util.Validate;

import java.util.LinkedList;

public class TimeParkour
        extends AbstractParkour {

    public TimeParkour(String id, int maxTime) {
        super(id, new LinkedList<>(), maxTime, 0);
        Validate.isTrue(maxTime > 0, "Max time cannot be 0 or less, use a Default parkour object");
    }

    @Override
    public void start() {
        // TODO: method to start a parkour game
    }
}
