package me.cxmilo.parkour.parkour.impl;

import me.cxmilo.parkour.parkour.AbstractParkour;

import java.util.LinkedList;

public class DefaultParkour
        extends AbstractParkour {

    public DefaultParkour(String id) {
        super(id, new LinkedList<>(), 0, 0);
    }

    @Override
    public void start() {
        // TODO: method to start a parkour game
    }
}
