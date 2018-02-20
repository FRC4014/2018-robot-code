package wclaw;

import javax.management.timer.Timer;

import org.usfirst.frc4014.powerup.OI;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeCube extends Command{

	private final WheeledClaw wheeledClaw;
	private final OI oi;
	private long initTimestamp;
	
	public IntakeCube(WheeledClaw wheeledClaw, OI oi) {
		this.wheeledClaw = wheeledClaw;
		this.oi = oi;
		requires(wheeledClaw);
	}
	
	protected void initialize() {
		initTimestamp = Timer.ONE_WEEK;
		wheeledClaw.open();
	}
	
	protected void execute() {
		wheeledClaw.intake();
		if(oi.clawButton.get()) {
			wheeledClaw.close();
			initTimestamp = System.currentTimeMillis();
		}
	}
	
	@Override
	protected boolean isFinished() {
		return (System.currentTimeMillis() - initTimestamp >= 500);
	}

}
