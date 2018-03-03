package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.clawlift.ClawLift;
import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestPosition extends CommandGroup{
	public TestPosition(DriveTrain driveTrain, ClawLift clawLift){
		addParallel(new ReleaseFred(clawLift));
	}
}
