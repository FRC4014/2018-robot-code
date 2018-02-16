package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.GameData;
import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterPosition extends CommandGroup{
	private final DriveTrain driveTrain;

	public CenterPosition(DriveTrain driveTrain) {
		this.driveTrain = driveTrain;
//	}
//	
//	public void initialize() {
		Double speed = 0.65;

		String rawPrefs = "";
		if(GameData.isAllySwitchOnLeft()) {
			System.out.println("going left");
			rawPrefs = Preferences.getInstance().getString("AutoCenterLeft", "d:25,p:-67.5,d:141.16,p:67.4,d:42");
		} else {
			System.out.println("going right");
			rawPrefs = Preferences.getInstance().getString("AutoCenterRight", "d:25,p:65,d:133.125,p:-65.4,d:42");
		}
		System.out.println("Autonomous prefs: " + rawPrefs);

		String[] prefs = rawPrefs.split(",");
		double d1 = getAsDouble(prefs[0]);
		double pA = getAsDouble(prefs[1]);
		double d2 = getAsDouble(prefs[2]);
		double pB = getAsDouble(prefs[3]);
		double d3 = getAsDouble(prefs[4]);

		addSequential(new DriveByDistance(driveTrain, speed, d1));
		addSequential(new CustomPIDPivotByGyro(pA));
		addSequential(new DriveByDistance(driveTrain, speed, d2));
		addSequential(new CustomPIDPivotByGyro(pB));
		addSequential(new DriveByDistance(driveTrain, speed, d3));

//		if(isAllySwitchOnLeft) {
//			System.out.println("going left");
//			addSequential(new DriveByDistance(driveTrain, 1, 25));
//			addSequential(new CustomPIDPivotByGyro(-67.5));
//			addSequential(new DriveByDistance(driveTrain, 1.0, 141.16));
//			addSequential(new CustomPIDPivotByGyro(67.4));
//			addSequential(new DriveByDistance(driveTrain, 1, 42));
//		} else {
//			System.out.println("going right");
//			addSequential(new DriveByDistance(driveTrain, 1, 25));
//			addSequential(new CustomPIDPivotByGyro(65));
//			addSequential(new DriveByDistance(driveTrain, 1, 133.125));
//			addSequential(new CustomPIDPivotByGyro(-65));
//			addSequential(new DriveByDistance(driveTrain, 1, 42));
//		}

		//TODO: add drop code
		//TODO: extend fred (if we end up using fred)
	}

	/**
	 * Gets the value as a double. 
	 * Assumes format of pref to have the double after a colon, e.g., "b:25" or "p:23.5"
	 */
	private Double getAsDouble(String pref) {
		return Double.valueOf(pref.split(":")[1]);
	}
}
