package org.usfirst.frc4014.powerup.wheeledclaw.wclaw;

import edu.wpi.first.wpilibj.command.Command;

public class OutputCube extends Command{
	
	private final WheeledClaw wheeledClaw;
	private long initTimestamp;
	
	public OutputCube(WheeledClaw wheeledClaw) {
		this.wheeledClaw = wheeledClaw;
//		requires(wheeledClaw);
	}

	protected void initialize() {
	    System.out.println("Output cube initialized");
//	    wheeledClaw.open(); //ONLY FOR TESTING EXTERMINATE THIS LINE BEFORE COMPETITION
		initTimestamp = System.currentTimeMillis();
	}
	
	protected void execute() {
	    System.out.println("Output Cube time is: " + (System.currentTimeMillis() - initTimestamp));
	    if (System.currentTimeMillis() - initTimestamp < 600) {
	    		wheeledClaw.output();
	    		System.out.println("Outputing Cube");
	    }
	    if (System.currentTimeMillis() - initTimestamp > 300) {
    			wheeledClaw.open();
	    }
	}
	
	protected void end() {
	    System.out.println("Output Cube is finished");
		wheeledClaw.hold();
	}
	
	@Override
	protected boolean isFinished() {
		return (System.currentTimeMillis() - initTimestamp > 600);
	}

}
