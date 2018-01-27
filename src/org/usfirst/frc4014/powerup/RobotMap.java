package org.usfirst.frc4014.powerup;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    private static WPI_TalonSRX driveTrainLeftMotorA;
    private static WPI_TalonSRX driveTrainLeftMotorB;
    private static SpeedControllerGroup driveTrainLeftMotorGroup;
    private static WPI_TalonSRX driveTrainRightMotorA;
    private static WPI_TalonSRX driveTrainRightMotorB;
    private static SpeedControllerGroup driveTrainRightMotorGroup;
    public static DifferentialDrive driveTrainDifferentialDrive;
    
    public static Encoder leftEncoder;
    public static Encoder rightEncoder;
    
    public static void init() {
    		// The numbers of these WPI_TalonSRXs have to match the CAN configuration.
    		// Other things will be on the CAN bus too.
    		// We could have a CANSettings.java that has constants that clearly show 
    		// the needed configuration so whoever sets up the CAN can look in one place 
    		// rather than hunting though the robot code.
        driveTrainLeftMotorA = new WPI_TalonSRX(3);
        driveTrainLeftMotorB = new WPI_TalonSRX(2);
        driveTrainLeftMotorGroup = new SpeedControllerGroup(driveTrainLeftMotorA, driveTrainLeftMotorB);
        driveTrainRightMotorA = new WPI_TalonSRX(5);
        driveTrainRightMotorB = new WPI_TalonSRX(4);
        driveTrainRightMotorGroup = new SpeedControllerGroup(driveTrainRightMotorA, driveTrainRightMotorB);

        driveTrainDifferentialDrive = new DifferentialDrive(driveTrainRightMotorGroup, driveTrainLeftMotorGroup);

        driveTrainDifferentialDrive.setName("Differential Drive");
        driveTrainDifferentialDrive.setSubsystem("DriveTrain");
        LiveWindow.add(driveTrainDifferentialDrive);
        // RobotBuilder generated this next line that uses deprecated library code. We can do better.
        // LiveWindow.addActuator("DriveTrain", "Differential Drive", driveTrainDifferentialDrive);
        
//        driveTrainDifferentialDrive.setSafetyEnabled(true);
//        driveTrainDifferentialDrive.setExpiration(0.1);
//        driveTrainDifferentialDrive.setMaxOutput(1.0);
        
        leftEncoder = new Encoder(DPIO.LEFT_ENCODER_A_CHANNEL, DPIO.LEFT_ENCODER_B_CHANNEL, true, Encoder.EncodingType.k4X);
        rightEncoder = new Encoder(DPIO.RIGHT_ENCODER_A_CHANNEL, DPIO.RIGHT_ENCODER_B_CHANNEL, false, Encoder.EncodingType.k4X);
    }
}
