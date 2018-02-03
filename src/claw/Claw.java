package claw;

import org.usfirst.frc4014.powerup.OI;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem{

	private OI oi;
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ControlClawWithButtons(this, oi));
	}

}
