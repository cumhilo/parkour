package me.cxmilo.parkour.util;

import me.cxmilo.parkour.ParkourPlugin;
import org.bukkit.configuration.ConfigurationSection;

import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

public class TImeUtil {

    private static final ConfigurationSection SECTION = ParkourPlugin.getInstance().getConfig().getConfigurationSection("timeFormats");

    // Credits to Gilberto García @FixedDev on github
    public static String durationToHumanTime(long time) {
        StringJoiner joiner = new StringJoiner(" ");

        long seconds = time / 1000;

        int unitValue = Math.toIntExact(seconds / TimeUnit.DAYS.toSeconds(7));
        if (unitValue > 0) {
            seconds %= TimeUnit.DAYS.toSeconds(7);

            String unit;

            if (unitValue == 1) {
                unit = SECTION.getString("week");
            } else {
                unit = SECTION.getString("weeks");
            }

            joiner.add(unitValue + " " + unit);
        }

        unitValue = Math.toIntExact(seconds / TimeUnit.DAYS.toSeconds(1));
        if (unitValue > 0) {
            seconds %= TimeUnit.DAYS.toSeconds(1);

            String unit;

            if (unitValue == 1) {
                unit = SECTION.getString("day");
            } else {
                unit = SECTION.getString("days");
            }

            joiner.add(unitValue + " " + unit);
        }

        unitValue = Math.toIntExact(seconds / TimeUnit.HOURS.toSeconds(1));
        if (unitValue > 0) {
            seconds %= TimeUnit.HOURS.toSeconds(1);
            String unit;

            if (unitValue == 1) {
                unit = SECTION.getString("hour");
            } else {
                unit = SECTION.getString("hours");
            }

            joiner.add(unitValue + " " + unit);
        }

        unitValue = Math.toIntExact(seconds / TimeUnit.MINUTES.toSeconds(1));
        if (unitValue > 0) {
            seconds %= TimeUnit.MINUTES.toSeconds(1);
            String unit;

            if (unitValue == 1) {
                unit = SECTION.getString("minute");
            } else {
                unit = SECTION.getString("minutes");
            }

            joiner.add(unitValue + " " + unit);
        }

        if (seconds > 0 || joiner.length() == 0) {
            String unit;

            if (seconds == 1) {
                unit = SECTION.getString("second");
            } else {
                unit = SECTION.getString("seconds");
            }

            joiner.add(seconds + " " + unit);
        }

        return joiner.toString();
    }
}
