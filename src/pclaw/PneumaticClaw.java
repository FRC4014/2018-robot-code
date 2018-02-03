package pclaw;

import org.usfirst.frc4014.powerup.OI;
import org.usfirst.frc4014.powerup.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PneumaticClaw extends Subsystem{

	private OI oi;
	
	public PneumaticClaw (OI oi) {
		this.oi = oi;
		
		oi.c.toggleWhenPressed(new ControlClawWithButtons(this));
	}
	
	@Override
	protected void initDefaultCommand() {

	}
	
	protected void open() {
		RobotMap.clawSolenoidA.set(DoubleSolenoid.Value.kForward);
		RobotMap.clawSolenoidB.set(DoubleSolenoid.Value.kForward);
	}
	
	protected void close() {
		RobotMap.clawSolenoidA.set(DoubleSolenoid.Value.kReverse);
		RobotMap.clawSolenoidB.set(DoubleSolenoid.Value.kReverse);
	}

}
