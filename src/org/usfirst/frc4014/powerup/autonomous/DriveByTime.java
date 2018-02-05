package org.usfirst.frc4014.powerup.autonomous;
import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveByTime extends Command {
	
	private final DriveTrain driveTrain;
	private double speed;
	private double time;
	private double timeCounter;

    public DriveByTime(DriveTrain driveTrain, double speed, double time) {
    		this.driveTrain = driveTrain;
    		this.speed = speed;
    		this.time = time;
    		this.timeCounter = 0;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    		driveTrain.drive(speed);
    		timeCounter = timeCounter + 0.02; //TODO find a delta time value, or make one
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    		if(timeCounter >= time) {
    			return true;
    		} else {
    			return false;
    		}
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
