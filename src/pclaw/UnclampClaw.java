package pclaw;

import edu.wpi.first.wpilibj.command.Command;

public class UnclampClaw extends Command{

private final PneumaticClaw claw;
	
	public UnclampClaw(PneumaticClaw claw) {
		this.claw = claw;
		requires(claw);
	}
	
	protected void initialize() {
		claw.open();
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
