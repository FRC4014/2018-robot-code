package pclaw;

import org.usfirst.frc4014.powerup.OI;

import edu.wpi.first.wpilibj.command.Command;

public class ControlClawWithButtons extends Command{

	private final PneumaticClaw claw;
	
	public ControlClawWithButtons(PneumaticClaw claw) {
		this.claw = claw;
		requires(claw);
	}
	
	//initialize close the claw
	protected void initialize() {
		claw.close();
	}
	
	//end resets the claw to open position
	protected void end() {
		claw.open();
	}
	
	@Override
	protected boolean isFinished() {
		// always need a claw, this is always true
		return false;
	}

}
