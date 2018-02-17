package org.usfirst.frc4014.powerup.autonomous;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc4014.powerup.GameData;
import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

public class CenterPosition extends CommandGroup {

    public CenterPosition(DriveTrain driveTrain, GameData gameData) {

        Double speed = Preferences.getInstance().getDouble("AutoDriveSpeed", 1.0);

        AutoPrefs prefs;
        if (gameData.isAllyScaleOnLeft()) {
            System.out.println("Robot on center, going left");
            prefs = new AutoPrefs("AutoCenterLeft", "d:25,p:-67.5,d:141.16,p:67.4,d:42");
        } else {
            System.out.println("Robot on center, going right");
            prefs = new AutoPrefs("AutoCenterRight", "d:25,p:65,d:45,p:-65.4,d:42");
        }

        addSequential(new DriveByDistance(driveTrain, speed, prefs.prefs[0]));
        addSequential(new CustomPIDPivotByGyro(prefs.prefs[1]));
        addSequential(new DriveByDistance(driveTrain, speed, prefs.prefs[2]));
        addSequential(new CustomPIDPivotByGyro(prefs.prefs[3]));
        addSequential(new DriveByDistance(driveTrain, speed, prefs.prefs[4]));

        //TODO: add drop code
        //TODO: extend fred (if we end up using fred)
    }
}
