package org.usfirst.frc4014.powerup.autonomous;


import edu.wpi.first.wpilibj.Preferences;

public class AutoPrefs {

    private final String stringPrefs;

    private final double[] prefs;
    public AutoPrefs(String prefsKey, String backup) {
        stringPrefs = Preferences.getInstance().getString(prefsKey, backup);
        prefs = parseRawPrefs(stringPrefs);
    }

    private double[] parseRawPrefs(String stringPrefs) {
        String[] steps = stringPrefs.split(",");
        double[] p = new double[steps.length];
        for (int i =0; i < p.length; i++) {
            p[i] = Double.valueOf(steps[i].split(":")[1]);
        }
        return p;
    }

    public String getStringPrefs() {
        return stringPrefs;
    }

    public double get(int index) {
        return prefs[index];
    }
}
