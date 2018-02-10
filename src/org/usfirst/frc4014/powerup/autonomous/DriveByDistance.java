package org.usfirst.frc4014.powerup.autonomous;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Preferences;
import org.usfirst.frc4014.powerup.RobotMap;
import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveByDistance extends Command{

	private final DriveTrain driveTrain;
	
	private double speed;
	private double distance;

	//////// PID stuff -- start
	private double p, i, d = 0;
	private double integral, previousError;
	private final double setPoint = 0;
	private boolean isInsideTolerance = false;
	private int postDone = 0;
	private boolean first = true;

	private final AHRS ahrs;

	private double maxSpeed;
	private double minSpeed;
	private double tolerance;
	private long initTimestamp;
	//////// PID stuff -- end

	public DriveByDistance(DriveTrain driveTrain, double speed, double distance) {
		this.ahrs = RobotMap.AHRS;
		this.driveTrain = driveTrain;
		this.speed = speed;
		this.distance = distance;
		requires(driveTrain);
	}

	@Override
	protected void initialize() {
		System.out.println("DriveByDistance.initialize(): distance = " + distance);
		driveTrain.resetEncoders();

		initPIDControl();
	}

	private void initPIDControl() {
		initTimestamp = System.currentTimeMillis();
		ahrs.reset();
		p = Preferences.getInstance().getDouble("ddP", 0.5);
		i = Preferences.getInstance().getDouble("ddi", 0);
		d = Preferences.getInstance().getDouble("ddd", 0);
		maxSpeed = Preferences.getInstance().getDouble("ddPivotMaxSpeed", 0.8);
		minSpeed = Preferences.getInstance().getDouble("ddPivotMinSpeed", 0.2);
		tolerance = Preferences.getInstance().getDouble("ddPivotTolerance", 1.0);
		integral = previousError = 0;
		isInsideTolerance = false;
		postDone = 0;
		first = true;
		System.out.println("\n\n\n==== DriveByDistance =======================================================");
		System.out.println("p: " + p + " | i: " + i + " | d: " + d + " | setPoint: " + setPoint);
	}

	@Override
	protected void execute() {
		final double angle = ahrs.getAngle();
		double error = setPoint - angle;
		error = first ? setPoint : error; // in case ahrs.reset() isn't isInsideTolerance (only observed first time, so kludging it)
		first = false;
		double rcw = 0;
		double rotation = 0;
		isInsideTolerance = Math.abs(error) < tolerance;
		if (!isInsideTolerance) {
			integral += error * 0.02; // 0.02 because it's normal timing for IterativeRobot.
			double derivative = (error - previousError) / 0.02;
			rcw = (p * error) + (i * integral) + (d * derivative);

			double modRcw = Math.abs(rcw)/* / (setPoint * .25)*/; //setpoint was 0, maybe dividing by 0 causes problems?
			rotation = Math.max(minSpeed, Math.min(modRcw, maxSpeed));
			rotation = rcw < 0 ? -rotation : rotation;
			
			driveTrain.arcadeDrive(-speed, rotation);
		} else {
			driveTrain.arcadeDrive(-speed, 0);
		}
		System.out.println("isInsideTolerance: " + isInsideTolerance + " | angle: " + angle + " | error: " + error + " | raw rcw: " + rcw
				+ " | rotation: " + rotation +" | speed: " + speed);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO: this is just a placeholder.... need to have it based on target distance etc.
		double rightDistance = RobotMap.rightEncoder.getDistance();
		double leftDistance = RobotMap.leftEncoder.getDistance();
		boolean finished = leftDistance  >= distance;
		System.out.println("DriveByDistance.isFinished(): Distance[R : " + rightDistance + " | L = " + leftDistance
				+ "]    Raw[R = " + RobotMap.rightEncoder.getRaw() + " | L = " + RobotMap.leftEncoder.getRaw() 
				+ "]    finished = " + finished);
		return finished;
	}

}
