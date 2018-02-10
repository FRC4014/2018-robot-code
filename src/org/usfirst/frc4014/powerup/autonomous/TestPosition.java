package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestPosition extends CommandGroup{
	public TestPosition(DriveTrain driveTrain){
		addSequential (new DriveByDistance(driveTrain, 1, 
				Preferences.getInstance().getDouble("DriveDistanceInches", 12)));
	}
}
