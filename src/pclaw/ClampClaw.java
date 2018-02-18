package pclaw;

import edu.wpi.first.wpilibj.command.Command;

public class ClampClaw extends Command{

	private final PneumaticClaw claw;
	
	public ClampClaw(PneumaticClaw claw) {
		this.claw = claw;
		requires(claw);
	}
	
	//initialize close the claw
	protected void initialize() {
		claw.close();
	}
	
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
