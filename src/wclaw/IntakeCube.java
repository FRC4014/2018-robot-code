package wclaw;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeCube extends Command{

	private final WheeledClaw wheeledClaw;
	private long initTimestamp;
	
	public IntakeCube(WheeledClaw wheeledClaw) {
		this.wheeledClaw = wheeledClaw;
		requires(wheeledClaw);
	}
	
	protected void initialize() {
		initTimestamp = System.currentTimeMillis();
		wheeledClaw.open();
	}
	
	protected void execute() {
		wheeledClaw.intake();
	}
	
	protected void end() {
		wheeledClaw.close();
	}
	
	@Override
	protected boolean isFinished() {
		// this is a toggle command, so buttons will take care of this
		return false;
	}

}
