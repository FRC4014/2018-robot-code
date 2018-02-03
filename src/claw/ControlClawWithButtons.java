package claw;

import org.usfirst.frc4014.powerup.OI;

import edu.wpi.first.wpilibj.command.Command;

public class ControlClawWithButtons extends Command{

	private final Claw claw;
	private final OI oi;
	
	public ControlClawWithButtons(Claw claw, OI oi) {
		this.claw = claw;
		this.oi = oi;
		requires(claw);
	}
	
	@Override
	protected boolean isFinished() {
		// always need a claw, this is always true
		return false;
	}

}
