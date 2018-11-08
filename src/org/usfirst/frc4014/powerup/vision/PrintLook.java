package org.usfirst.frc4014.powerup.vision;

import edu.wpi.first.wpilibj.command.Command;

public class PrintLook extends Command{
	private final PixyCam pixycam;
	
	public PrintLook(PixyCam pixycam) {
		super();
		this.pixycam = pixycam;
		requires(pixycam);
	}
	
	protected void execute() {
		pixycam.testPixy();
	}
	
	@Override
	protected boolean isFinished() {
		// is never finished
		return false;
	}
	
	
}
