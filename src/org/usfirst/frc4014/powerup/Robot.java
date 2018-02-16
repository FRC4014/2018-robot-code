package org.usfirst.frc4014.powerup;

import org.usfirst.frc4014.powerup.autonomous.CenterPosition;
import org.usfirst.frc4014.powerup.autonomous.CustomPIDPivotByGyro;
import org.usfirst.frc4014.powerup.autonomous.DriveByDistance;
import org.usfirst.frc4014.powerup.autonomous.DriveByTime;
import org.usfirst.frc4014.powerup.autonomous.TestPosition;
import org.usfirst.frc4014.powerup.clawlift.ClawLift;
import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
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

    public static OI oi;
    public static DriveTrain driveTrain;
    public static ClawLift clawLift;
    public static PneumaticClaw pneumaticClaw;
    public static WheeledClaw wheeledClaw;
    
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
        clawLift = new ClawLift(oi);
        pneumaticClaw = new PneumaticClaw(oi);
        wheeledClaw = new WheeledClaw(oi);
        
//        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
//        camera.setFPS(10);

        // Add commands to Autonomous Sendable Chooser
        chooser.addObject("Drive By Time", new DriveByTime(driveTrain, 1, 3));
        chooser.addObject("Drive by Distance", new DriveByDistance(driveTrain, 
        		Preferences.getInstance().getDouble("driveSpeed", .5),
        		Preferences.getInstance().getDouble("DriveDistanceInches", 12)));
        chooser.addObject("Custom PID Pivot", new CustomPIDPivotByGyro(Preferences.getInstance().getDouble("PivotSetPoint", 90)));
        chooser.addObject("Center Position", new CenterPosition(driveTrain));
        chooser.addDefault("TestPosition", new TestPosition(driveTrain));


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
		GameData.gameData = DriverStation.getInstance().getGameSpecificMessage();;
		System.out.println("gameData: " + GameData.gameData);
		
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
