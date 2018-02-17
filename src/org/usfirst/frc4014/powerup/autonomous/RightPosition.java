package org.usfirst.frc4014.powerup.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc4014.powerup.GameData;
import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

public class RightPosition extends CommandGroup {

    public RightPosition(DriveTrain driveTrain, GameData gameData) {
        if (gameData.isAllySwitchOnLeft()) {
        	addSequential(new DriveByDistance(driveTrain, 1, 25));
            addSequential(new CustomPIDPivotByGyro(25));
            addSequential(new DriveByDistance(driveTrain, 1, 127));
            addSequential(new CustomPIDPivotByGyro(-25));
            if (gameData.isAllyScaleOnLeft()) {
                addSequential(new DriveByDistance(driveTrain, 1, 159));
                //TODO add drop code
            }
        } else {
            addSequential(new DriveByDistance(driveTrain, 1, 90));
            //TODO add drop code
        }
        //TODO extend fred (if we end up using fred)
    }
}
