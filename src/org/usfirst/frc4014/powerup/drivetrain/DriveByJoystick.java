package org.usfirst.frc4014.powerup.drivetrain;

import org.usfirst.frc4014.powerup.OI;

import edu.wpi.first.wpilibj.command.Command;

public class DriveByJoystick extends Command{

	private final DriveTrain drivetrain;
	private final OI oi;
	public DriveByJoystick(DriveTrain drivetrain, OI oi) {
		super();
		this.oi = oi;
		this.drivetrain = drivetrain;
		requires(drivetrain);
	}

	protected void execute() {
		drivetrain.drive(oi.getDriverJoystick());
	}
	
	@Override
	protected boolean isFinished() {
		// we're always driving, so this is always false
		return false;
	}

}
