package org.usfirst.frc4014.powerup.wheeledclaw.wclaw;

import javax.management.timer.Timer;

import org.usfirst.frc4014.powerup.OI;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeCube extends Command{

	private final WheeledClaw wheeledClaw;
	private final OI oi;
	private long turnOffTimestamp;
	private long initTimestamp;
	
	public IntakeCube(WheeledClaw wheeledClaw, OI oi) {
		this.wheeledClaw = wheeledClaw;
		this.oi = oi;
		requires(wheeledClaw);
	}
	
	protected void initialize() {
		turnOffTimestamp = Timer.ONE_MINUTE;
		initTimestamp = System.currentTimeMillis();
		wheeledClaw.open();
	}
	
	protected void execute() {
		wheeledClaw.intake();
		if(oi.clawButton.get() && System.currentTimeMillis() - initTimestamp > 1000) {
			wheeledClaw.close();
			initTimestamp = System.currentTimeMillis();
		}
	}
	
	@Override
	protected boolean isFinished() {
		return (System.currentTimeMillis() - turnOffTimestamp >= 500);
	}

}
