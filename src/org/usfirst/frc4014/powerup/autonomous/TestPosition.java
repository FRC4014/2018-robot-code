package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestPosition extends CommandGroup{
	public TestPosition (DriveTrain driveTrain, AHRS ahrs){
		addSequential (new CustomPIDPivotByGyro(ahrs, 90));
	}
}
