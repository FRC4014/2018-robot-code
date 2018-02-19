package org.usfirst.frc4014.powerup.autonomous;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc4014.powerup.GameData;
import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

public class RightPosition extends CommandGroup {

    public RightPosition(DriveTrain driveTrain, GameData gameData) {
        TrackTimeCommand.TimeTracker timeTracker =
                new TrackTimeCommand.TimeTracker("CenterPosition");

        addSequential(new TrackTimeCommand(timeTracker, true));
        if (gameData.isAllySwitchOnLeft()) {
            addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), 25));
            addSequential(new CustomPIDPivotByGyro(25));
            addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), 127));
            addSequential(new CustomPIDPivotByGyro(-25));
            if (gameData.isAllyScaleOnLeft()) {
                addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), 159));
                //TODO add drop code
            }
        } else {
            addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), Preferences.getInstance().getDouble("rightPositionDistance", 90)));
            addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveSlowSpeed", 1), 15));
            //TODO add drop code
        }
        addSequential(new TrackTimeCommand(timeTracker, false));
        //TODO extend fred (if we end up using fred)
    }
}
