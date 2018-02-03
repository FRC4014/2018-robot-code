package org.usfirst.frc4014.powerup;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
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
	private static final double WHEEL_DIAMETER = 6;
	private static final double GEAR_RATIO = 4.4;
	private static final int ENCODER_RESOLUTION = 2048;
	private static final double PULSES_PER_ROTATION = ENCODER_RESOLUTION;
	private static final double DISTANCE_PER_PULSE = 
			Math.PI * WHEEL_DIAMETER * GEAR_RATIO / PULSES_PER_ROTATION;

	public static WPI_TalonSRX driveTrainLeftMotorA;
	public static WPI_TalonSRX driveTrainLeftMotorB;
    private static SpeedControllerGroup driveTrainLeftMotorGroup;
    public static WPI_TalonSRX driveTrainRightMotorA;
    public static WPI_TalonSRX driveTrainRightMotorB;
    private static SpeedControllerGroup driveTrainRightMotorGroup;
    public static DifferentialDrive driveTrainDifferentialDrive;
    
    public static WPI_TalonSRX clawMotorA;
    public static WPI_TalonSRX clawMotorB;
    
    public static Compressor compressor;
    public static Solenoid clawSolenoidA;
    public static Solenoid clawSolenoidB;
    
    public static Encoder leftEncoder;
    public static Encoder rightEncoder;
    
	public static WPI_TalonSRX clawAscentMotor;

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
        
        clawMotorA = new WPI_TalonSRX(6);
        clawMotorB = new WPI_TalonSRX(7);
        
        compressor = new Compressor(0);
        compressor.setClosedLoopControl(true);
        clawSolenoidA = new Solenoid(1);
        clawSolenoidB = new Solenoid(2);
        
        
        // RobotBuilder generated this next line that uses deprecated library code. We can do better.
        // LiveWindow.addActuator("DriveTrain", "Differential Drive", driveTrainDifferentialDrive);
        
//        driveTrainDifferentialDrive.setSafetyEnabled(true);
//        driveTrainDifferentialDrive.setExpiration(0.1);
//        driveTrainDifferentialDrive.setMaxOutput(1.0);
        
        leftEncoder = new Encoder(DPIO.LEFT_ENCODER_A_CHANNEL, DPIO.LEFT_ENCODER_B_CHANNEL, false, Encoder.EncodingType.k4X);
        rightEncoder = new Encoder(DPIO.RIGHT_ENCODER_A_CHANNEL, DPIO.RIGHT_ENCODER_B_CHANNEL, true, Encoder.EncodingType.k4X);
		leftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
		rightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
		System.out.println("DriveMap: DISTANCE_PER_PULSE = " + DISTANCE_PER_PULSE);
    }
}
