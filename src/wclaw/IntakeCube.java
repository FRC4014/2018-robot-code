package wclaw;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeCube extends Command{

	private final WheeledClaw wheeledClaw;
	private long initTimestamp;
	
	public IntakeCube(WheeledClaw wheeledClaw) {
		this.wheeledClaw = wheeledClaw;
	}
	
	protected void initialize() {
		initTimestamp = System.currentTimeMillis();
	}
	
	protected void execute() {
		wheeledClaw.intake();
	}
	
	@Override
	protected boolean isFinished() {
		return (System.currentTimeMillis() - initTimestamp > 1000);
	}

}
