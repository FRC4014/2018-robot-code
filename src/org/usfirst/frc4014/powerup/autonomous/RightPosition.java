package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightPosition extends CommandGroup{
	public RightPosition(DriveTrain driveTrain, boolean isAllySwitchOnLeft,  boolean isAllyScaleOnLeft, AHRS ahrs) {
		if(isAllySwitchOnLeft) {
			addSequential(new DriveByDistance(driveTrain, 1, 26));
			addSequential(new PivotByGyro(driveTrain, ahrs, -22.25));
			addSequential(new DriveByDistance(driveTrain, 1, 95.1));
			addSequential(new PivotByGyro(driveTrain, ahrs, 22.25));
			if (isAllyScaleOnLeft) {
				addSequential(new DriveByDistance(driveTrain, 1, 184));
				//TODO add drop code
			}
		} else {
			addSequential(new DriveByDistance(driveTrain, 1, 101));
			//TODO add drop code
		}
		//TODO extend fred (if we end up using fred)
	}
}
