package org.usfirst.frc4014.powerup.clawlift;

import org.usfirst.frc4014.powerup.OI;
import org.usfirst.frc4014.powerup.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClawLift extends Subsystem {

	//TODO make a method for when we first release fred that turns 1 motor a lot so we get rid of initial slack
	private OI oi;
	
	private double tensionFactor;
	
	public ClawLift(OI oi) {
		this.oi = oi;
	}
	
	@Override
	protected void initDefaultCommand() {
//		setDefaultCommand(new AscendClawByJoystick(this, oi));
	}

	public void ascend (Joystick joystick) {
		double speed = -joystick.getY() + .1;
		System.out.println("joystick speed is: " + speed);
		if (speed > 0 /*&& !RobotMap.upperLimit.get()*/) {
			System.out.println("going down!");
			RobotMap.clawAscentMotorA.set(speed + .1);
		} else if (speed < 0 /*&& !RobotMap.lowerLimit.get()*/){
			System.out.println("going up!");
			RobotMap.clawAscentMotorA.set(speed + .1);
		}
		
	}
	
	public void release() {
		RobotMap.fredReleaseSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	

}
