package wclaw;

import edu.wpi.first.wpilibj.command.Command;

public class OutputCube extends Command{
	
	private final WheeledClaw wheeledClaw;
	private long initTimestamp;
	
	public OutputCube(WheeledClaw wheeledClaw) {
		this.wheeledClaw = wheeledClaw;
	}

	protected void initialize() {
		initTimestamp = System.currentTimeMillis();
	}
	
	protected void execute() {
		wheeledClaw.output();
	}
	
	@Override
	protected boolean isFinished() {
		return (System.currentTimeMillis() - initTimestamp > 1000);
	}

}
