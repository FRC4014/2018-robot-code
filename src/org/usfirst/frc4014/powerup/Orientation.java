package org.usfirst.frc4014.powerup;

import org.usfirst.frc4014.powerup.autonomous.AutoPrefs;

/**
 * Provides orientation factors for Y (forward/reverse) and Z (turn left/right)
 * based on the preference 'Orientation'.
 * Defaults to 'y:-1,z:-1' to fit Derf (finalbot).
 */
public final class Orientation {

    // Default to Derf's orientation.
    private static double y = -1;
    private static double z = -1;

    /**
     * This should be called in Robot.robotInit().
     */
    static void init() {
        AutoPrefs prefs = new AutoPrefs("Orientation", "y:-1,x:-1");
        y = prefs.prefs[0];
        z = prefs.prefs[1];
    }

    public static double y(double rawY) {
        return rawY * y;
    }

    public static double z(double rawZ) {
        return rawZ * z;
    }
}
