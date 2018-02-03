package org.usfirst.frc4014.powerup.drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleGearRatio extends Command{

	private final DriveTrain driveTrain;
	
	public ToggleGearRatio(DriveTrain driveTrain) {
		this.driveTrain = driveTrain;
	}
	
	protected void initialize() {
		driveTrain.slowGearRatio();
	}
	
	protected void end() {
		driveTrain.fastGearRatio();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
