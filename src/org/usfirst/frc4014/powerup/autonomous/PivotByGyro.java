package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class PivotByGyro extends PIDCommand{

	private final DriveTrain driveTrain;
	private double angle;
	
	public PivotByGyro(DriveTrain driveTrain, double angle) {
		super(0.7, 0, 0); //TODO these are just placeholder values, need to test to find what actually works
		this.driveTrain = driveTrain;
		this.angle = angle;
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
