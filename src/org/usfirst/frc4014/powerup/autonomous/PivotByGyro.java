package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class PivotByGyro extends PIDCommand{

	private final DriveTrain driveTrain;
	private double angle;
	private AHRS ahrs;
	private boolean onTarget;
	
	public PivotByGyro(DriveTrain driveTrain, AHRS ahrs, double angle) {
		super(0.7, 0, 0); //TODO these are just placeholder values, need to test to find what actually works
		this.driveTrain = driveTrain;
		this.angle = angle;
		this.ahrs = ahrs;
		
		requires(driveTrain);
		setSetpoint(angle);
	}

	protected void initialize() {
		ahrs.reset();
		
		getPIDController().setPID(0.7,0,0);
		getPIDController().setAbsoluteTolerance(5); //TODO this value needs to be recalculated once we redo the math for movement
	}
	
	@Override
	protected double returnPIDInput() {
		double currentAngle = ahrs.getAngle();
		return currentAngle;
	}

	@Override
	protected void usePIDOutput(double output) {
		driveTrain.rotate(output);
	}

	@Override
	protected boolean isFinished() {
		return getPIDController().onTarget();
	}

}
