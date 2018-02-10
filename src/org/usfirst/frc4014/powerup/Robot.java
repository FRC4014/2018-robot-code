package org.usfirst.frc4014.powerup;

import org.usfirst.frc4014.powerup.autonomous.CustomPIDPivotByGyro;
import org.usfirst.frc4014.powerup.autonomous.DriveByDistance;
import org.usfirst.frc4014.powerup.autonomous.DriveByTime;
import org.usfirst.frc4014.powerup.autonomous.TestPosition;
import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import pclaw.PneumaticClaw;
import wclaw.WheeledClaw;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    public static AHRS ahrs = new AHRS(SPI.Port.kMXP);
    public static OI oi;
    public static DriveTrain driveTrain;
    public static PneumaticClaw pneumaticClaw;
    public static WheeledClaw wheeledClaw;
    
    public boolean isAllySwitchOnLeft;
    public boolean isAllyScaleOnLeft;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
    	  // since oi is constructed before subsystems, it must not contain any commands
        oi = new OI();
        RobotMap.init();
        driveTrain = new DriveTrain(oi);
        pneumaticClaw = new PneumaticClaw(oi);
        wheeledClaw = new WheeledClaw(oi);

        // Add commands to Autonomous Sendable Chooser
        chooser.addObject("Drive By Time", new DriveByTime(driveTrain, 1, 3));
        chooser.addObject("Drive by Distance", new DriveByDistance(driveTrain, .5,
        		Preferences.getInstance().getDouble("DriveDistanceInches", 12)));
        chooser.addObject("Custom PID Pivot", new CustomPIDPivotByGyro(ahrs, 21));
        chooser.addDefault("TestPosition", new TestPosition(driveTrain, ahrs));

        SmartDashboard.putData("Autonomous mode chooser", chooser);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
    	String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(0) == 'L')
		{
			isAllySwitchOnLeft = true;
		} else {
			isAllySwitchOnLeft = false;
		}
		
		if(gameData.charAt(1) == 'L')
		{
			isAllyScaleOnLeft = true;
		} else {
			isAllyScaleOnLeft = false;
		}
		
		driveTrain.setNeutralMode(NeutralMode.Brake);

        autonomousCommand = chooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
		driveTrain.setNeutralMode(NeutralMode.Coast);
    	
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
}
