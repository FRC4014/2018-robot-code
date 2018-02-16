package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterPosition extends CommandGroup{
	public CenterPosition(DriveTrain driveTrain, boolean isAllySwitchOnLeft) {
		addSequential(new DriveByDistance(driveTrain, 1, 25));
		if(isAllySwitchOnLeft) {
			addSequential(new CustomPIDPivotByGyro(67.5));
			addSequential(new DriveByDistance(driveTrain, 1, 141.16));
			addSequential(new CustomPIDPivotByGyro(-67.4));
		} else {
			addSequential(new CustomPIDPivotByGyro(-65));
			addSequential(new DriveByDistance(driveTrain, 1, 133.125));
			addSequential(new CustomPIDPivotByGyro(65));
		}
		addSequential(new DriveByDistance(driveTrain, 1, 42));
		//TODO add drop code
		//TODO extend fred (if we end up using fred)
	}
}
