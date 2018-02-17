package org.usfirst.frc4014.powerup.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc4014.powerup.GameData;
import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

public class RightPosition extends CommandGroup {

    public RightPosition(DriveTrain driveTrain, GameData gameData) {
        if (gameData.isAllySwitchOnLeft()) {
            addSequential(new CustomPIDPivotByGyro(-53.62));
            addSequential(new DriveByDistance(driveTrain, 1, 236));
            addSequential(new CustomPIDPivotByGyro(53.62));
            if (gameData.isAllyScaleOnLeft()) {
                addSequential(new DriveByDistance(driveTrain, 1, 184));
                //TODO add drop code
            }
        } else {
            addSequential(new DriveByDistance(driveTrain, 1, 140));
            //TODO add drop code
        }
        //TODO extend fred (if we end up using fred)
    }
}
