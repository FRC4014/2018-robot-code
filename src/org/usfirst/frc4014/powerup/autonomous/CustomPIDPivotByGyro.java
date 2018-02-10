package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class CustomPIDPivotByGyro extends Command {
	private double p, i, d = 1;
	private double integral, previousError, setPoint = 0;
	private boolean acceptable = false;
	private int postDone = 0;
	private boolean first = true;

	private final AHRS ahrs;

	private double maxSpeed;
	private double minSpeed;
	private double tolerance;
	private long initTimestamp;

	public CustomPIDPivotByGyro(double setPoint) {
		// this.setPoint = setPoint;
		this.ahrs = RobotMap.AHRS;
		this.setPoint = setPoint;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		initTimestamp = System.currentTimeMillis();
		ahrs.reset();
		p = Preferences.getInstance().getDouble("P", 0.5);
		i = Preferences.getInstance().getDouble("i", 0);
		d = Preferences.getInstance().getDouble("d", 0);
		maxSpeed = Preferences.getInstance().getDouble("PivotMaxSpeed", 0.8);
		minSpeed = Preferences.getInstance().getDouble("PivotMinSpeed", 0.2);
		tolerance = Preferences.getInstance().getDouble("PivotTolerance", 1.0);
		integral = previousError = 0;
		acceptable = false;
		postDone = 0;
		first = true;
		System.out.println("\n\n\n===========================================================");
		System.out.println("p: " + p + " | i: " + i + " | d: " + d + " | setPoint: " + setPoint);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		final double angle = ahrs.getAngle();
		double error = setPoint - angle;
		error = first ? setPoint : error; // in case ahrs.reset() isn't acceptable (only observed first time, so kludging it)
		first = false;
		double rcw = 0;
		double speed = 0;
		acceptable = Math.abs(error) < tolerance;
		if (!acceptable) {
			integral += error * 0.02; // 0.02 because it's normal timing for IterativeRobot.
			double derivative = (error - previousError) / 0.02;
			rcw = (p * error) + (i * integral) + (d * derivative);

			double modRcw = Math.abs(rcw) / (setPoint * .25);
			speed = Math.max(minSpeed, Math.min(modRcw, maxSpeed));
			speed = rcw < 0 ? -speed : speed;
			RobotMap.driveTrainDifferentialDrive.arcadeDrive(0, speed);
		}
		System.out.println("acceptable: " + acceptable + " | angle: " + angle + " | error: " + error + " | raw rcw: " + rcw
				+ " | speed: " + speed);
	}

	@Override
	protected boolean isFinished() {
		if (acceptable) {
			postDone++;
			if (postDone == 1) {
				System.out.println("Milliseconds: " + (System.currentTimeMillis() - initTimestamp));
			}
			System.out.println("Post pivot angle " + postDone + ": " + ahrs.getAngle());
			return (postDone > 3);
		} else {
			return false;
		}
	}

}
