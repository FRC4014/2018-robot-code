package org.usfirst.frc4014.powerup.clawlift;

import org.usfirst.frc4014.powerup.OI;
import org.usfirst.frc4014.powerup.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClawLift extends Subsystem {

	private OI oi;

	// Don't want the motor to run until fred is fully deployed.
    // For now, the ReleaseFred command is responsible for setting enableMotor.
    // TODO: Would be better to use a limit switch to know when the lock-shaft is in place on Fred.
    public boolean enableMotor = false;

	public ClawLift(OI oi) {
		this.oi = oi;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new AscendClawByJoystick(this, oi));
	}

	public void ascend (Joystick joystick) {
	    // TODO: What's with all these `+ .1` things?
		double speed = -joystick.getY() + .1;
//		System.out.println("joystick speed is: " + speed);
		if (speed > 0 /*&& !RobotMap.upperLimit.get()*/) {
//			System.out.println("going down!");
			RobotMap.clawAscentMotorA.set(speed + .1);
		} else if (speed < 0 /*&& !RobotMap.lowerLimit.get()*/){
//			System.out.println("going up!");
			RobotMap.clawAscentMotorA.set(speed + .1);
		}

	}

	public void ascend(double speed) {
		RobotMap.clawAscentMotorA.set(speed);
	}
	
	public void releaseFred() {
//		RobotMap.fredReleaseSolenoid.set(DoubleSolenoid.Value.kReverse);
		RobotMap.fredReleaseServo.setAngle(180);
	}

	public void resetEncoder() {
		RobotMap.clawAscentEncoder.reset();
	}
}
