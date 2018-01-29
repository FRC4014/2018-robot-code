package org.usfirst.frc4014.powerup.claw;

import org.usfirst.frc4014.powerup.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public void ascend (Joystick joystick) {
		double speed = joystick.getY();
		RobotMap.clawAscentMotor.set(speed);

	}

}
