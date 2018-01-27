package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveByDistance extends Command{

	
	


	private final DriveTrain driveTrain;
	
	private double speed;
	private double distance;
			
	public DriveByDistance(DriveTrain driveTrain, double speed, double distance) {
		this.driveTrain = driveTrain;
		this.speed = speed;
		this.distance = distance;
		requires(driveTrain);
	}

	@Override
	protected void initialize() {
		System.out.println("DriveByDistance.initialize()");
		driveTrain.resetEncoders();
	}
	
	@Override
	protected void execute() {
		System.out.println("DriveByDistance.execute()");
		driveTrain.drive(speed);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO: this is just a placeholder.... need to have it based on target distance etc.
		boolean finished = driveTrain.getRightWheelDistance() >= distance;
		System.out.println("DriveByDistance.isFinished(): " + finished);
		return finished;
	}

}
