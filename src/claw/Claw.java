package claw;

import org.usfirst.frc4014.powerup.OI;
import org.usfirst.frc4014.powerup.RobotMap;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem{

	private OI oi;
	
	public Claw (OI oi) {
		this.oi = oi;
		
		oi.c.toggleWhenPressed(new ControlClawWithButtons(this));
	}
	
	@Override
	protected void initDefaultCommand() {

	}
	
	protected void open() {
		RobotMap.clawSolenoidA.set(true);
		RobotMap.clawSolenoidB.set(true);
	}
	
	protected void close() {
		RobotMap.clawSolenoidA.set(false);
		RobotMap.clawSolenoidB.set(false);
	}

}
