package org.usfirst.frc4014.powerup.clawlift;

import org.usfirst.frc4014.powerup.OI;
import org.usfirst.frc4014.powerup.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClawLift extends Subsystem {

	private OI oi;

	// Don't want the motor to run until fred is fully deployed.
    // For now, the ReleaseFred command is responsible for setting enableMotor.
    // TODO: Would be better to use a limit switch to know when the lock-shaft is in place on Fred.
    public boolean enableMotor = true;
    private double holdSteady = Preferences.getInstance().getDouble("HoldSteady", .2);

	public ClawLift(OI oi) {
		this.oi = oi;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new AscendClawByJoystick(this, oi)); // TODO: uncomment this line
	}

	public void ascend (Joystick joystick) {
		double speed = -joystick.getY() + holdSteady;
//		System.out.println("claw hight is" + RobotMap.clawAscentEncoder.get());
//		speed = Math.max(0, speed);
		if (speed > holdSteady + .1 /*&& !RobotMap.upperLimit.get()*/) {
		    RobotMap.clawAscentSolenoid.set(DoubleSolenoid.Value.kForward);
//			System.out.println("going down!");
			RobotMap.clawAscentMotorA.set(speed);
		} else if (speed < holdSteady -.1 /*&& !RobotMap.lowerLimit.get()*/){
		    RobotMap.clawAscentSolenoid.set(DoubleSolenoid.Value.kForward);
//			System.out.println("going up!");
			RobotMap.clawAscentMotorA.set(speed);
		} else {
		    RobotMap.clawAscentSolenoid.set(DoubleSolenoid.Value.kReverse);
		    RobotMap.clawAscentMotorA.set(0);
		}
		

	}

	public void ascend(double speed) {
	    RobotMap.clawAscentSolenoid.set(DoubleSolenoid.Value.kForward);
//      System.out.println("going up!");
        RobotMap.clawAscentMotorA.set(speed);
	}
	
	public void lock() {
	    RobotMap.clawAscentSolenoid.set(DoubleSolenoid.Value.kReverse);
        RobotMap.clawAscentMotorA.set(0);
	}
	
	public void releaseFred() {
//		RobotMap.fredReleaseSolenoid.set(DoubleSolenoid.Value.kReverse);
		RobotMap.fredReleaseServo.setAngle(180);
	}

	public void resetEncoder() {
//		RobotMap.clawAscentEncoder.reset();
	}
}
