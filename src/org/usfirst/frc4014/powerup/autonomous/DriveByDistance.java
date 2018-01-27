package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveByDistance extends Command{

	
	
	private static double WHEEL_DIAMETER = 6;
	private static double GEAR_RATIO = 5.45;
	private static double PULSES_PER_ROTATION = 4;
	private static double DISTANCE_PER_PULSE = 
			Math.PI * WHEEL_DIAMETER / GEAR_RATIO * PULSES_PER_ROTATION;

	private final DriveTrain driveTrain;
	
	private double speed;
	private double distance;
			
	public DriveByDistance(DriveTrain driveTrain, double speed, double distance) {
		this.driveTrain = driveTrain;
		this.speed = speed;
		this.distance = distance;
	}
	@Override
	protected void initialize() {
		driveTrain.resetEncoders();
	}
	
	int count = sampleEncoder.get();
	sampleEncoder.getDistance();
	
	@Override
	protected boolean isFinished() {
		// TODO: this is just a placeholder.... need to have it based on target distance etc.
		return driveTrain.getRightWheelDistance() > distance;
	}

}
