package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterPosition extends CommandGroup{
	public CenterPosition(DriveTrain driveTrain, boolean isAllySwitchOnLeft) {
		if(isAllySwitchOnLeft) {
			addSequential(new CustomPIDPivotByGyro(21));
			addSequential(new DriveByDistance(driveTrain, 1, 149.96));
			addSequential(new CustomPIDPivotByGyro(-21));
			//TODO add drop code
		} else {
			addSequential(new CustomPIDPivotByGyro(-21));
			addSequential(new DriveByDistance(driveTrain, 1, 149.96));
			addSequential(new CustomPIDPivotByGyro(21));
			//TODO add drop code
		}
		//TODO extend fred (if we end up using fred)
	}
}
