package wclaw;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeCube extends Command{

	private final WheeledClaw wheeledClaw;
	
	public IntakeCube(WheeledClaw wheeledClaw) {
		this.wheeledClaw = wheeledClaw;
	}
	
	protected void execute() {
		wheeledClaw.intake();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
