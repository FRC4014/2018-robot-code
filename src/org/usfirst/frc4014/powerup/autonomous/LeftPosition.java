package org.usfirst.frc4014.powerup.autonomous;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc4014.powerup.wheeledclaw.wclaw.OutputCube;
import org.usfirst.frc4014.powerup.wheeledclaw.wclaw.WheeledClaw;

import org.usfirst.frc4014.powerup.GameData;
import org.usfirst.frc4014.powerup.clawlift.ClawLift;
import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

public class LeftPosition extends CommandGroup {

    public LeftPosition(DriveTrain driveTrain, GameData gameData, ClawLift clawLift, WheeledClaw wheeledClaw) {
        TrackTimeCommand.TimeTracker timeTracker =
                new TrackTimeCommand.TimeTracker("CenterPosition");

        addSequential(new TrackTimeCommand(timeTracker, true));
        addParallel(new ReleaseFred(clawLift));
        if (gameData.isAllySwitchOnLeft()) {
            addParallel(new AscendClawByDistance(clawLift, 20));
            addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), 91));
            addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveSlowSpeed", 1), 15));
            addSequential(new OutputCube(wheeledClaw));
        } else {
            addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), 25));
            addSequential(new Pivot(-18));
            addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), 116.77));
            addSequential(new Pivot(11.4));
            if (gameData.isAllyScaleOnLeft()) {
                addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), 100));
            }
        }
        addSequential(new TrackTimeCommand(timeTracker, false));
        //TODO extend fred (if we end up using fred)
    }
}
