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
		tensionFactor = Preferences.getInstance().getDouble("tensionFactor", .95);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new AscendClawByJoystick(this, oi));
	}

	public void ascend (Joystick joystick) {
		double speed = -joystick.getY();
		if (speed > 0 && !RobotMap.upperLimit.get()) {
			RobotMap.clawAscentMotorA.set(speed);
			RobotMap.clawAscentMotorB.set(speed * tensionFactor);
		} else if (speed < 0 && !RobotMap.lowerLimit.get()){
			RobotMap.clawAscentMotorA.set(speed * tensionFactor);
			RobotMap.clawAscentMotorB.set(speed);
		}
		

	}
	
	public void release() {
		RobotMap.fredReleaseSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	

}
