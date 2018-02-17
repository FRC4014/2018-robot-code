package org.usfirst.frc4014.powerup.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc4014.powerup.GameData;
import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

public class LeftPosition extends CommandGroup {

    public LeftPosition(DriveTrain driveTrain, GameData gameData) {
        if (gameData.isAllySwitchOnLeft()) {
            addSequential(new DriveByDistance(driveTrain, 1, 141));
            //TODO add drop code
        } else {
        		addSequential(new DriveByDistance(driveTrain, 1, 25));
            addSequential(new CustomPIDPivotByGyro(-16.1));
            addSequential(new DriveByDistance(driveTrain, 1, 116.77));
            addSequential(new CustomPIDPivotByGyro(9.5));
            if (gameData.isAllyScaleOnLeft()) {
                addSequential(new DriveByDistance(driveTrain, 1, 159));
                //TODO add drop code
            }
        }
        //TODO extend fred (if we end up using fred)
    }
}
