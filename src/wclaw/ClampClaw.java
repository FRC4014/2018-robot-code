package wclaw;

import edu.wpi.first.wpilibj.command.Command;
import pclaw.PneumaticClaw;

public class ClampClaw extends Command{

	private final WheeledClaw claw;
	private double initTime;
	
	public ClampClaw(WheeledClaw claw) {
		this.claw = claw;
		requires(claw);
	}
	
	//initialize close the claw
	protected void initialize() {
		initTime = System.currentTimeMillis();
	}
	
	protected void end() {
		
	}
	
	@Override
	protected boolean isFinished() {
		return (System.currentTimeMillis() - initTime >= 500);
	}

}
