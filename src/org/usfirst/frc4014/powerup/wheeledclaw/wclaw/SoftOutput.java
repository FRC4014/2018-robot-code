package org.usfirst.frc4014.powerup.wheeledclaw.wclaw;

import edu.wpi.first.wpilibj.command.Command;

public class SoftOutput extends Command{

	private final WheeledClaw wheeledClaw;
	private long initTimestamp;
	
	public SoftOutput(WheeledClaw wheeledClaw) {
		this.wheeledClaw = wheeledClaw;
	}
	
	protected void initialize() {
		initTimestamp = System.currentTimeMillis();
		wheeledClaw.open();
	}
	
	protected void execute() {
//		if (System.currentTimeMillis() - initTimestamp < 300) {
	        System.out.println("slowly outputting cube");
			wheeledClaw.slowOutput();
//		}
	}
	
	protected void end() {
		wheeledClaw.hold();
	}
	
	@Override
	protected boolean isFinished() {
		return (System.currentTimeMillis() - initTimestamp >= 300);
	}

}
