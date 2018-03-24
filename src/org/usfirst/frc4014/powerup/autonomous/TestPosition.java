package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.clawlift.ClawLift;
import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;
import org.usfirst.frc4014.powerup.wheeledclaw.wclaw.OutputCube;
import org.usfirst.frc4014.powerup.wheeledclaw.wclaw.SimpleIntake;
import org.usfirst.frc4014.powerup.wheeledclaw.wclaw.WheeledClaw;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestPosition extends CommandGroup{
	public TestPosition(DriveTrain driveTrain, ClawLift clawLift, WheeledClaw wheeledClaw){
	    addSequential(new SimpleIntake(wheeledClaw));
//	    addSequential(new AscendClawByDistance(clawLift, 20));
	    addSequential(new AscendClawToTop(clawLift, 20));
		addSequential(new OutputCube(wheeledClaw));
//		addSequential(m_parent, m_startTime);
	}
}
