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
//		requires(wheeledClaw);
	}
	
	protected void initialize() {
	    System.out.println("Intiake cube initialized!");
		turnOffTimestamp = System.currentTimeMillis() + Timer.ONE_MINUTE;
		initTimestamp = System.currentTimeMillis();
		wheeledClaw.open();
	}
	
	protected void execute() {
	    System.out.println("Input cube system time is: " + (System.currentTimeMillis() - initTimestamp) + "| Stop Time is: " + (System.currentTimeMillis() - turnOffTimestamp));
	    if (System.currentTimeMillis() - turnOffTimestamp < 500) {
	        System.out.println("intaking Cube");
	        wheeledClaw.intake();
	    }
		if(oi.clawButton.get() && System.currentTimeMillis() - initTimestamp > 200) {
		    System.out.println("clawButton called in intakeCube!");
			wheeledClaw.close();
			turnOffTimestamp = System.currentTimeMillis();
		}
	}
	
	protected void end() {
	    System.out.println("Intake cube has ended!");
	    wheeledClaw.hold();
	}
	
	@Override
	protected boolean isFinished() {
		return (System.currentTimeMillis() - turnOffTimestamp >= 500);
	}

}
