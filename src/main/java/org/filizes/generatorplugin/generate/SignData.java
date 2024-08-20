package org.filizes.generatorplugin.generate;

import org.bukkit.Location;

public class SignData {
    private final Location location;
    private final String[] lines;

    public SignData(Location location, String[] lines) {
        this.location = location;
        this.lines = lines;
    }

    public Location getLocation() {
        return location;
    }

    public String[] getLines() {
        return lines;
    }
}