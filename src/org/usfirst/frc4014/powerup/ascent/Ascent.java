package org.usfirst.frc4014.powerup.ascent;

import org.usfirst.frc4014.powerup.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Ascent extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public void drive(Joystick joystick) {
		double speed = joystick.getY();
		RobotMap.clawAscentMotor.set(speed);
		
	}

}
