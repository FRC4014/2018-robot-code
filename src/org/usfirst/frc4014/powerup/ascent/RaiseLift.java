package org.usfirst.frc4014.powerup.ascent;

import edu.wpi.first.wpilibj.command.Command;

public class RaiseLift extends Command{

	private Ascent ascent;
	
	public RaiseLift(Ascent ascent) {
		this.ascent = ascent;
	}
	
	protected void execute() {
		ascent.lower();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
