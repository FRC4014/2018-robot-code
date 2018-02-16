package org.usfirst.frc4014.powerup;

import edu.wpi.first.wpilibj.Preferences;

public final class GameData {

	/** This class is for static use only. */
	private GameData() {
		/* static use only */
	}
	
	private static String robotPostition = "C";
	private static String fieldData = "RRR";
	private static String rawPrefs = "d:25,p:65,d:45,p:-65.4,d:46"; // from center-right
	private static double[] prefs = {};

	/**
	 * @param robotPosition One of "R", "C", or "L".
	 * @param fieldData From Field, e.g., "RLR"
	 */
	static void initialize(String robotPosition, String fieldData) {
		GameData.robotPostition = robotPosition;
		GameData.fieldData = fieldData;		
		loadAutoPrefs();
	}
	
	private static void loadAutoPrefs() {
		
		if ("C".equals(robotPostition) && isAllyScaleOnLeft()) {
			System.out.println("Robot on center, going left");
			rawPrefs = Preferences.getInstance().getString("AutoCenterLeft", "d:25,p:-67.5,d:141.16,p:67.4,d:42");
		}
		else if ("C".equals(robotPostition) && isAllyScaleOnRight()) {
			System.out.println("Robot on center, going right");
			rawPrefs = Preferences.getInstance().getString("AutoCenterRight", "d:25,p:65,d:133.125,p:-65.4,d:42");
		}
		else if ("L".equals(robotPostition) && isAllyScaleOnLeft()) {
			System.out.println("Robot on left, going left");
			// TODO: load it...
		}
		else if ("L".equals(robotPostition) && isAllyScaleOnRight()) {
			System.out.println("Robot on left, going right");
			// TODO: load it...
		}
		else if ("R".equals(robotPostition) && isAllyScaleOnLeft()) {
			System.out.println("Robot on right, going left");
			// TODO: load it...
		}
		else if ("R".equals(robotPostition) && isAllyScaleOnRight()) {
			System.out.println("Robot on right, going right");
			// TODO: load it...
		}

		prefs = parseRawPrefs(rawPrefs);
	}

	private static double[] parseRawPrefs(String stringPrefs) {
		String[] steps = stringPrefs.split(",");
		double[] p = new double[steps.length];
		for (int i =0; i < p.length; i++) {
			p[i] = getAsDouble(steps[i]);
		}
		return p;
	}
	
	/**
	 * Gets the value as a double. 
	 * Assumes format of pref to have the double after a colon, e.g., "b:25" or "p:23.5"
	 */
	private static double getAsDouble(String pref) {
		return Double.valueOf(pref.split(":")[1]);
	}
	
	public static boolean isAllySwitchOnLeft() {
		return fieldData.charAt(0) == 'L';
	}
	
	public static boolean isAllySwitchOnRight() {
		return fieldData.charAt(0) == 'R';
	}
	
	public static boolean isAllyScaleOnLeft() {
		return fieldData.charAt(1) == 'L';
	}
	
	public static boolean isAllyScaleOnRight() {
		return fieldData.charAt(1) == 'R';
	}
	
	public static double getAutoPref(int index) {
		return GameData.prefs[index];
	}

}
