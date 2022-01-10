package me.cxmilo.parkour.parkour.impl;

import me.cxmilo.parkour.parkour.AbstractParkour;
import me.yushust.message.util.Validate;

import java.util.LinkedList;

public class AttemptsParkour
        extends AbstractParkour {

    public AttemptsParkour(String id, int maxAttempts) {
        super(id, new LinkedList<>(), 0, maxAttempts);
        Validate.isTrue(maxAttempts > 0, "Max attempts cannot be 0 or less, use a Default parkour object");
    }

    @Override
    public void start() {
        // TODO: method to start a parkour game
    }
}
