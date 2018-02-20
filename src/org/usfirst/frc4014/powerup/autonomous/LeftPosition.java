package org.usfirst.frc4014.powerup.autonomous;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import wclaw.OutputCube;
import wclaw.WheeledClaw;

import org.usfirst.frc4014.powerup.GameData;
import org.usfirst.frc4014.powerup.clawlift.ClawLift;
import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

public class LeftPosition extends CommandGroup {

    public LeftPosition(DriveTrain driveTrain, GameData gameData, ClawLift clawLift, WheeledClaw wheeledClaw) {
        TrackTimeCommand.TimeTracker timeTracker =
                new TrackTimeCommand.TimeTracker("CenterPosition");

        addSequential(new TrackTimeCommand(timeTracker, true));
        addSequential(new ReleaseFred(clawLift));
        if (gameData.isAllySwitchOnLeft()) {
            addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), 91));
            addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveSlowSpeed", 1), 15));
            addSequential(new OutputCube(wheeledClaw));
        } else {
        		addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), 25));
            addSequential(new CustomPIDPivotByGyro(-16.1));
            addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), 116.77));
            addSequential(new CustomPIDPivotByGyro(9.5));
            if (gameData.isAllyScaleOnLeft()) {
                addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), 159));
            }
        }
        addSequential(new TrackTimeCommand(timeTracker, false));
        //TODO extend fred (if we end up using fred)
    }
}
