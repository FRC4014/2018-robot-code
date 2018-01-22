package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterPosition extends CommandGroup{
	public CenterPosition(DriveTrain driveTrain, boolean isAllySwitchOnLeft) {
		if(isAllySwitchOnLeft) {
			addSequential(new PivotByGyro(driveTrain, 21));
			addSequential(new DriveByDistance(driveTrain, 1, 149.96));
			addSequential(new PivotByGyro(driveTrain, -21));
			//TODO add drop code
		} else {
			addSequential(new PivotByGyro(driveTrain, -21));
			addSequential(new DriveByDistance(driveTrain, 1, 149.96));
			addSequential(new PivotByGyro(driveTrain, 21));
			//TODO add drop code
		}
		//TODO extend fred (if we end up using fred)
	}
}
