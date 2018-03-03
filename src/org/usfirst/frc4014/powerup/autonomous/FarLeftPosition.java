package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.GameData;
import org.usfirst.frc4014.powerup.clawlift.ClawLift;
import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;
import org.usfirst.frc4014.powerup.wheeledclaw.wclaw.WheeledClaw;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class FarLeftPosition extends CommandGroup{
    
    public FarLeftPosition(DriveTrain driveTrain, GameData gameData, ClawLift clawLift, WheeledClaw wheeledClaw) {
        addParallel(new ReleaseFred(clawLift));
        addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), 168));
        if (gameData.isAllySwitchOnLeft()) {
        addSequential(new Pivot(90));
        addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveSlowSpeed", 1), 15));
        } else {
            addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), 120));
        }
    }

}
