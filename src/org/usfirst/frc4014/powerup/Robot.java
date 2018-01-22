package org.usfirst.frc4014.powerup;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc4014.powerup.autonomous.DriveByTime;
import org.usfirst.frc4014.powerup.drivetrain.DriveTrain;

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
    
    public boolean isAllySwitchOnLeft;

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

        // Add commands to Autonomous Sendable Chooser
        chooser.addDefault("Drive By Time", new DriveByTime(driveTrain, 1, 3));

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
    		String gameData;
    		gameData = DriverStation.getInstance().getGameSpecificMessage();
    		if(gameData.charAt(0) == 'L')
    		{
    			isAllySwitchOnLeft = true;
    		} else {
    			isAllySwitchOnLeft = false;
    		}
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
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