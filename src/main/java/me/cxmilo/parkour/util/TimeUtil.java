package me.cxmilo.parkour.util;

import me.cxmilo.parkour.ParkourPlugin;
import me.yushust.message.MessageHandler;

import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

public class TimeUtil {

    private static final MessageHandler MESSAGE_HANDLER = ParkourPlugin.getInstance().getMessageHandler();

    // Credits to Gilberto García @FixedDev on GitHub
    public static String durationToHumanTime(long time) {
        StringJoiner joiner = new StringJoiner(" ");

        long seconds = time / 1000;

        int unitValue = Math.toIntExact(seconds / TimeUnit.DAYS.toSeconds(7));
        if (unitValue > 0) {
            seconds %= TimeUnit.DAYS.toSeconds(7);

            String unit;

            if (unitValue == 1) {
                unit = MESSAGE_HANDLER.getMessage("timeFormats.week");
            } else {
                unit = MESSAGE_HANDLER.getMessage("timeFormats.weeks");
            }

            joiner.add(unitValue + " " + unit);
        }

        unitValue = Math.toIntExact(seconds / TimeUnit.DAYS.toSeconds(1));
        if (unitValue > 0) {
            seconds %= TimeUnit.DAYS.toSeconds(1);

            String unit;

            if (unitValue == 1) {
                unit = MESSAGE_HANDLER.getMessage("timeFormats.day");
            } else {
                unit = MESSAGE_HANDLER.getMessage("timeFormats.days");
            }

            joiner.add(unitValue + " " + unit);
        }

        unitValue = Math.toIntExact(seconds / TimeUnit.HOURS.toSeconds(1));
        if (unitValue > 0) {
            seconds %= TimeUnit.HOURS.toSeconds(1);
            String unit;

            if (unitValue == 1) {
                unit = MESSAGE_HANDLER.getMessage("timeFormats.hour");
            } else {
                unit = MESSAGE_HANDLER.getMessage("timeFormats.hours");
            }

            joiner.add(unitValue + " " + unit);
        }

        unitValue = Math.toIntExact(seconds / TimeUnit.MINUTES.toSeconds(1));
        if (unitValue > 0) {
            seconds %= TimeUnit.MINUTES.toSeconds(1);
            String unit;

            if (unitValue == 1) {
                unit = MESSAGE_HANDLER.getMessage("timeFormats.minute");
            } else {
                unit = MESSAGE_HANDLER.getMessage("timeFormats.minutes");
            }

            joiner.add(unitValue + " " + unit);
        }

        if (seconds > 0 || joiner.length() == 0) {
            String unit;

            if (seconds == 1) {
                unit = MESSAGE_HANDLER.getMessage("timeFormats.second");
            } else {
                unit = MESSAGE_HANDLER.getMessage("timeFormats.seconds");
            }

            joiner.add(seconds + " " + unit);
        }

        return joiner.toString();
    }
}
