package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class PivotByGyro extends PIDCommand{

	private final DriveTrain driveTrain;
	private double angle;
	private AHRS ahrs; 
	private boolean onTarget;
	private double speedFactor;
	private double p;
	private double i;
	private double d;
	
	public PivotByGyro(DriveTrain driveTrain, AHRS ahrs, double angle) {
		super(0.5, 0, 0); //TODO these are just placeholder values, need to test to find what actually works
		this.driveTrain = driveTrain;
		this.angle = angle;
		this.ahrs = ahrs;

		requires(driveTrain);
		
		getPIDController().setInputRange(-180, 180);
		getPIDController().setContinuous(true);
		getPIDController().setAbsoluteTolerance(1); //TODO this value needs to be recalculated once we redo the math for movement
	}

	protected void initialize() {
		ahrs.reset();
		
		getPIDController().setOutputRange(-0.7, 0.7);
		getPIDController().setSetpoint(angle);

		speedFactor = Preferences.getInstance().getDouble("PivotSpeedFactor", 1);

		p = Preferences.getInstance().getDouble("P", 1);
		i = Preferences.getInstance().getDouble("i", 1);
		d = Preferences.getInstance().getDouble("d", 1);
		getPIDController().setPID(p,i,d);
	}
	
	@Override
	protected double returnPIDInput() {
		double currentAngle = ahrs.getYaw();
		System.out.println("NavX thinks the angle is: " + currentAngle);
		return currentAngle;
		
	}

	@Override
	protected void usePIDOutput(double output) {
		driveTrain.rotate(output * speedFactor);
		System.out.println("Output is currently:" + output);
	}

	@Override
	protected boolean isFinished() {
		return getPIDController().onTarget();
	}

}
