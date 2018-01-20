package org.usfirst.frc4014.powerup;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static WPI_TalonSRX driveTrainLeftMotor;
    public static WPI_TalonSRX driveTrainRightMotor;
    public static DifferentialDrive driveTrainDifferentialDrive;

    public static void init() {
    		// The numbers of these WPI_TalonSRXs have to match the CAN configuration.
    		// Other things will be on the CAN bus too.
    		// We could have a CANSettings.java that has constants that clearly show 
    		// the needed configuration so whoever sets up the CAN can look in one place 
    		// rather than hunting though the robot code.
        driveTrainLeftMotor = new WPI_TalonSRX(0);
        driveTrainRightMotor = new WPI_TalonSRX(1);

        driveTrainDifferentialDrive = new DifferentialDrive(driveTrainRightMotor, driveTrainLeftMotor);

        driveTrainDifferentialDrive.setName("Differential Drive");
        driveTrainDifferentialDrive.setSubsystem("DriveTrain");
        LiveWindow.add(driveTrainDifferentialDrive);
        // RobotBuilder generated this next line that uses deprecated library code. We can do better.
        // LiveWindow.addActuator("DriveTrain", "Differential Drive", driveTrainDifferentialDrive);
        
        driveTrainDifferentialDrive.setSafetyEnabled(true);
        driveTrainDifferentialDrive.setExpiration(0.1);
        driveTrainDifferentialDrive.setMaxOutput(1.0);
    }
}
