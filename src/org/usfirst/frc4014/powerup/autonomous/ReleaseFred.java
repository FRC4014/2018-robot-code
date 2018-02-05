package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.clawlift.ClawLift;

import edu.wpi.first.wpilibj.command.Command;

public class ReleaseFred extends Command {

	private final ClawLift clawLift;
	
	public ReleaseFred(ClawLift clawlift) {
		this.clawLift = clawlift;
		requires(clawlift);
	}
	
	protected void initialize() {
		clawLift.release();
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
