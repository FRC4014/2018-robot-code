package org.usfirst.frc4014.powerup.autonomous;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc4014.powerup.wheeledclaw.wclaw.OutputCube;
import org.usfirst.frc4014.powerup.wheeledclaw.wclaw.SimpleIntake;
import org.usfirst.frc4014.powerup.wheeledclaw.wclaw.SoftOutput;
import org.usfirst.frc4014.powerup.wheeledclaw.wclaw.WheeledClaw;

import org.usfirst.frc4014.powerup.MultiPrefs;
import org.usfirst.frc4014.powerup.clawlift.ClawLift;
import org.usfirst.frc4014.powerup.GameData;
import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

public class CenterPosition extends CommandGroup {

    public CenterPosition(DriveTrain driveTrain, GameData gameData, ClawLift clawLift, WheeledClaw wheeledClaw) {

        Double speed = Preferences.getInstance().getDouble("AutoDriveSpeed", 1.0);

        MultiPrefs prefs;
        if (gameData.isAllySwitchOnLeft()) {
            System.out.println("Robot on center, going left");
            prefs = new MultiPrefs("AutoCenterLeft", "d:25,p:-67.5,d:141.16,p:67.4,d:33,d:15");
        } else {
            System.out.println("Robot on center, going right");
            prefs = new MultiPrefs("AutoCenterRight", "d:25,p:65,d:45,p:-65.4,d:33,d:15");
        }

        TrackTimeCommand.TimeTracker timeTracker =
                new TrackTimeCommand.TimeTracker("CenterPosition");

        addSequential(new TrackTimeCommand(timeTracker, true));
//        addParallel(new ReleaseFred(clawLift));
        addSequential(new SimpleIntake(wheeledClaw));
        addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), prefs.prefs[0]));
        addSequential(new Pivot(prefs.prefs[1]));
        addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), prefs.prefs[2]));
        addSequential(new Pivot(prefs.prefs[3]));
        addSequential(new SimpleIntake(wheeledClaw));
        addSequential(new AscendClawByDistance(clawLift, 20));
        addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), prefs.prefs[4]));
        addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveSlowSpeed", 1), prefs.prefs[5]));
//        addSequential(new OutputCube(wheeledClaw));
        addSequential(new SoftOutput(wheeledClaw));
//        System.out.println("Pretending to output cube");
        addSequential(new TrackTimeCommand(timeTracker, false));

    }
}
