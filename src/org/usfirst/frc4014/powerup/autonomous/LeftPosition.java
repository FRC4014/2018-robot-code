package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftPosition extends CommandGroup{
	public LeftPosition(DriveTrain driveTrain, boolean isAllySwitchOnLeft, boolean isAllyScaleOnLeft, AHRS ahrs) {
		if(isAllySwitchOnLeft) {
			addSequential(new DriveByDistance(driveTrain, 1, 140));
			//TODO add drop code
		} else {
			addSequential(new CustomPIDPivotByGyro(ahrs, 53.62));
			addSequential(new DriveByDistance(driveTrain, 1, 236));
			addSequential(new CustomPIDPivotByGyro(ahrs, -53.62));
			if (isAllyScaleOnLeft) {
				addSequential(new DriveByDistance(driveTrain, 1, 184));
				//TODO add drop code
			}
		}
		//TODO extend fred (if we end up using fred)
	}
}
