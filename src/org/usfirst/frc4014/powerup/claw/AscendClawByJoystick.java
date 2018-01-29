package org.usfirst.frc4014.powerup.claw;

import org.usfirst.frc4014.powerup.OI;

import edu.wpi.first.wpilibj.command.Command;

public class AscendClawByJoystick extends Command {

	private final Claw claw;
	private final OI oi;

	public AscendClawByJoystick(Claw claw, OI oi) {
		super();
		this.oi = oi;
		this.claw = claw;
		requires(claw);
	}

	protected void execute() {
		claw.ascend(oi.getDriverJoystick());
	}

	@Override
	protected boolean isFinished() {
		// we're always driving, so this is always false
		return false;
	}

}
