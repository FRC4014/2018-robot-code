package org.usfirst.frc4014.powerup.claw;

import org.usfirst.frc4014.powerup.OI;
import org.usfirst.frc4014.powerup.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {

	private OI oi;
	
	public Claw(OI oi) {
		this.oi = oi;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new AscendClawByJoystick(this, oi));
	}

	public void ascend (Joystick joystick) {
		double speed = joystick.getY();
		RobotMap.clawAscentMotor.set(speed);

	}

}
