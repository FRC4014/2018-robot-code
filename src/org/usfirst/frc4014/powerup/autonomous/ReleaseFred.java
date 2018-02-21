package org.usfirst.frc4014.powerup.autonomous;

import edu.wpi.first.wpilibj.Preferences;
import org.usfirst.frc4014.powerup.clawlift.ClawLift;

import edu.wpi.first.wpilibj.command.Command;

public class ReleaseFred extends Command {

	private final ClawLift clawLift;
	private long initTimestamp;
	private long durationMillis;
	
	public ReleaseFred(ClawLift clawlift) {
		this.clawLift = clawlift;
		requires(clawlift);
	}

	@Override
	protected void initialize() {
		initTimestamp = System.currentTimeMillis();
		durationMillis = Preferences.getInstance().getLong("FredReleaseDuration", 0);
		clawLift.releaseFred();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO: Would be better to use a limit switch to know when the lock-shaft is in place on Fred.
		boolean isFredDeployed = durationMillis >= (System.currentTimeMillis() - initTimestamp);
		clawLift.enableMotor = isFredDeployed;
		return isFredDeployed;
	}

}
