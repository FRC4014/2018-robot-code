package wclaw;

import edu.wpi.first.wpilibj.command.Command;

public class OutputCube extends Command{
	
	private final WheeledClaw wheeledClaw;
	
	public OutputCube(WheeledClaw wheeledClaw) {
		this.wheeledClaw = wheeledClaw;
	}

	protected void execute() {
		wheeledClaw.output();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
