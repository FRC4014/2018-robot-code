package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestPosition extends CommandGroup{
	public TestPosition (DriveTrain driveTrain, AHRS ahrs){
<<<<<<< HEAD
		addSequential (new CustomPIDPivotByGyro(ahrs, 90));
=======
		addSequential (new DriveByDistance(driveTrain, 1, 30));
>>>>>>> 34f46d8484122644630c38f6138eebcd5eff9a8b
	}
}
