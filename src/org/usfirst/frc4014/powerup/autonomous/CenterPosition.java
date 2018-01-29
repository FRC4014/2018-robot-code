package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterPosition extends CommandGroup{
	public CenterPosition(DriveTrain driveTrain, boolean isAllySwitchOnLeft, AHRS ahrs) {
		if(isAllySwitchOnLeft) {
			addSequential(new DriveByDistance(driveTrain, 1, 6.5));
			addSequential(new PivotByGyro(driveTrain, ahrs, 31.53));
			addSequential(new DriveByDistance(driveTrain, 1, 103.25));
			addSequential(new PivotByGyro(driveTrain, ahrs, -31.53));
			addSequential(new DriveByDistance(driveTrain, 1, 6.5));
			//TODO add drop code
		} else {
			addSequential(new DriveByDistance(driveTrain, 1, 6.5));
			addSequential(new PivotByGyro(driveTrain, ahrs, -31.53));
			addSequential(new DriveByDistance(driveTrain, 1, 103.25));
			addSequential(new PivotByGyro(driveTrain, ahrs, 31.53));
			addSequential(new DriveByDistance(driveTrain, 1, 6.5));
			//TODO add drop code
		}
		//TODO extend fred (if we end up using fred)
	}
}
