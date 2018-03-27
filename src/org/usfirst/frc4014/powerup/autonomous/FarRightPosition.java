package org.usfirst.frc4014.powerup.autonomous;

import org.usfirst.frc4014.powerup.GameData;
import org.usfirst.frc4014.powerup.clawlift.ClawLift;
import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;
import org.usfirst.frc4014.powerup.wheeledclaw.wclaw.OutputCube;
import org.usfirst.frc4014.powerup.wheeledclaw.wclaw.SimpleIntake;
import org.usfirst.frc4014.powerup.wheeledclaw.wclaw.WheeledClaw;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class FarRightPosition extends CommandGroup {

    public FarRightPosition(DriveTrain driveTrain, GameData gameData, ClawLift clawLift, WheeledClaw wheeledClaw) {
//        addParallel(new ReleaseFred(clawLift));
        addSequential(new SimpleIntake(wheeledClaw));
        addSequential(new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), 142));
        if (gameData.isAllySwitchOnLeft()) {
            addSequential( new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), 60));
            addSequential(new Pivot(-90));
            addSequential( new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), 20));
            addSequential(new Pivot(90));
            addSequential( new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveFastSpeed", 1), 20));
//            addSequential(new AscendClawToTop(clawLift, 20));
//            addSequential(new HighSpeedOutput(wheeledClaw));


        } else {
            addSequential(new Pivot(-90));
            addParallel(new AscendClawByDistance(clawLift, 20));
            addSequential(
                    new DriveByDistance(driveTrain, Preferences.getInstance().getDouble("driveSlowSpeed", 1), 60));
            addSequential(new OutputCube(wheeledClaw));
        }

    }
}
