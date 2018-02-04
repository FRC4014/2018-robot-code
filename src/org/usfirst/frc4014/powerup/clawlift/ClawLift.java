package org.usfirst.frc4014.powerup.clawlift;

import org.usfirst.frc4014.powerup.OI;
import org.usfirst.frc4014.powerup.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClawLift extends Subsystem {

	private OI oi;
	
	private double tensionFactor;
	private double servoAngle;
	
	public ClawLift(OI oi) {
		this.oi = oi;
		tensionFactor = Preferences.getInstance().getDouble("tentionFactor", .95);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new AscendClawByJoystick(this, oi));
	}

	public void ascend (Joystick joystick) {
		double speed = joystick.getY();
		if (speed > 0) {
			RobotMap.clawAscentMotorA.set(speed);
			RobotMap.clawAscentMotorB.set(speed * tensionFactor);
		} else {
			RobotMap.clawAscentMotorA.set(speed * tensionFactor);
			RobotMap.clawAscentMotorB.set(speed);
		}
		

	}
	
	public void release() {
		RobotMap.fredReleaseSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	

}
