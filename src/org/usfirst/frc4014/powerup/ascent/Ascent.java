package org.usfirst.frc4014.powerup.ascent;

import org.usfirst.frc4014.powerup.OI;
import org.usfirst.frc4014.powerup.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Ascent extends Subsystem {

	private OI oi;
	
	public Ascent(OI oi) {
		this.oi = oi;
		oi.raiseLiftButton.toggleWhenActive(new RaiseLift(this));
		oi.lowerLiftButton.toggleWhenActive(new LowerLift(this));
	}

	@Override
	protected void initDefaultCommand() {
		// we don't have default commands, but eclipse freaks out if we dont have this bit here soooo...
	}

	protected void raise() {
		RobotMap.robotAscentMotor.set(1);
	}
	
	protected void lower() {
		RobotMap.robotAscentMotor.set(-1);
	}

}
