package org.usfirst.frc4014.powerup;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
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
	private static final double GEAR_RATIO = 3.0;//4.4;

	// DriveTrainEncoderResolution: amt10s are 2048 and E4T for shift gearbox is 1440
	private static final int ENCODER_RESOLUTION = Preferences.getInstance().getInt("DriveTrainEncoderResolution", 330 /*889*/);

	private static final double PULSES_PER_ROTATION = ENCODER_RESOLUTION;
	private static final double DISTANCE_PER_PULSE = (Math.PI * WHEEL_DIAMETER * 1 / PULSES_PER_ROTATION);
//	private static final double DISTANCE_PER_PULSE = 184/13750;

	public static AHRS AHRS;

	public static WPI_TalonSRX driveTrainLeftMotorA;
	public static WPI_TalonSRX driveTrainLeftMotorB;
	private static SpeedControllerGroup driveTrainLeftMotorGroup;
	public static WPI_TalonSRX driveTrainRightMotorA;
	public static WPI_TalonSRX driveTrainRightMotorB;
	private static SpeedControllerGroup driveTrainRightMotorGroup;
	public static DifferentialDrive driveTrainDifferentialDrive;
	public static DoubleSolenoid driveTrainSolenoidA;
	public static DoubleSolenoid driveTrainSolenoidB;

	public static WPI_TalonSRX clawMotorA;
	public static WPI_TalonSRX clawMotorB;

	public static Compressor compressor;
	public static DoubleSolenoid clawSolenoidA;
	public static DoubleSolenoid clawSolenoidB;

	public static Encoder leftEncoder;
	public static Encoder rightEncoder;

	public static WPI_TalonSRX clawAscentMotorA;
	public static WPI_TalonSRX clawAscentMotorB;
//	public static Encoder clawAscentEncoder;
	public static DoubleSolenoid clawAscentSolenoid;
//	public static DoubleSolenoid fredReleaseSolenoid;
	public static Servo fredReleaseServo;
	public static DigitalInput upperLimit;
	public static DigitalInput lowerLimit;

	public static WPI_TalonSRX robotAscentMotor;

	public static void init() {
        initNavX();
        initDriveTrain();
		initDriveTrainGearShifter();
		initClaw();
		initAscent();
	}

    private static void initNavX() {
        if (Preferences.getInstance().getBoolean("initNavX", true)) {
			System.out.println("RobotMap: initializing NavX");
            AHRS = new AHRS(SPI.Port.kMXP);
            AHRS.reset();
            AHRS.resetDisplacement();
		} else {
			System.out.println("RobotMap: NavX disabled");
		}
    }

    private static void initDriveTrain() {
		if (Preferences.getInstance().getBoolean("initDT", true)) {
			System.out.println("RobotMap: initializing DriveTrain");
			// The numbers of these WPI_TalonSRXs have to match the CAN configuration.
			// Other things will be on the CAN bus too.
			// We could have a CANSettings.java that has constants that clearly show
			// the needed configuration so whoever sets up the CAN can look in one place
			// rather than hunting though the robot code.
			driveTrainLeftMotorA = new WPI_TalonSRX(CAN.DRIVE_TRAIN_LEFT_MOTOR_A);
			driveTrainLeftMotorB = new WPI_TalonSRX(CAN.DRIVE_TRAIN_LEFT_MOTOR_B);
			driveTrainLeftMotorGroup = new SpeedControllerGroup(driveTrainLeftMotorA, driveTrainLeftMotorB);
			driveTrainRightMotorA = new WPI_TalonSRX(CAN.DRIVE_TRAIN_RIGHT_MOTOR_A);
			driveTrainRightMotorB = new WPI_TalonSRX(CAN.DRIVE_TRAIN_RIGHT_MOTOR_B);
			driveTrainRightMotorGroup = new SpeedControllerGroup(driveTrainRightMotorA, driveTrainRightMotorB);
			driveTrainDifferentialDrive = new DifferentialDrive(driveTrainRightMotorGroup, driveTrainLeftMotorGroup);
			// for the final robot where we only have 1 motor on each side
			// driveTrainDifferentialDrive = new DifferentialDrive(driveTrainRightMotorA, driveTrainLeftMotorA);
			driveTrainDifferentialDrive.setName("Differential Drive");
			driveTrainDifferentialDrive.setSubsystem("DriveTrain");
			LiveWindow.add(driveTrainDifferentialDrive);

			// RobotBuilder generated this next line that uses deprecated library code. We
			// can do better.
			// LiveWindow.addActuator("DriveTrain", "Differential Drive",
			// driveTrainDifferentialDrive);

			 driveTrainDifferentialDrive.setSafetyEnabled(false);
			// driveTrainDifferentialDrive.setExpiration(0.1);
			// driveTrainDifferentialDrive.setMaxOutput(1.0);

			leftEncoder = new Encoder(DPIO.LEFT_ENCODER_A_CHANNEL, DPIO.LEFT_ENCODER_B_CHANNEL, false,
					Encoder.EncodingType.k4X);
			rightEncoder = new Encoder(DPIO.RIGHT_ENCODER_A_CHANNEL, DPIO.RIGHT_ENCODER_B_CHANNEL, true,
					Encoder.EncodingType.k4X);
			leftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
			rightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
			System.out.println("--------- Encoder Resolution: " + ENCODER_RESOLUTION);
			System.out.println("DriveMap: DISTANCE_PER_PULSE = " + DISTANCE_PER_PULSE);
		} else {
			System.out.println("RobotMap: DriveTrain disabled");
		}
	}

	private static void initDriveTrainGearShifter() {
		if (Preferences.getInstance().getBoolean("initDTShifter", true)) {
			System.out.println("RobotMap: initializing DriveTrain Gear Shifter");
			driveTrainSolenoidA = new DoubleSolenoid(1, 6);
			driveTrainSolenoidB = new DoubleSolenoid(0, 7);
		} else {
			System.out.println("RobotMap: DriveTrain Gear Shifter disabled");
		}
	}

	private static void initClaw() {
		if (Preferences.getInstance().getBoolean("initClaw", true)) {
			System.out.println("RobotMap: initializing Claw");
			clawMotorA = new WPI_TalonSRX(CAN.CLAW_MOTOR_A);
			clawMotorB = new WPI_TalonSRX(CAN.CLAW_MOTOR_B);
//
	        compressor = new Compressor(0);
	        compressor.setClosedLoopControl(true);
			clawSolenoidA = new DoubleSolenoid(2, 5);
//
			clawAscentMotorA = new WPI_TalonSRX(CAN.CLAW_ASCENT_MOTOR_A);
			clawAscentSolenoid = new DoubleSolenoid(3,4);
			fredReleaseServo = new Servo(0);
//
//			clawAscentEncoder = new Encoder(DPIO.CLAW_ASCENT_ENCODER_A_CHANNEL, DPIO.CLAW_ASCENT_ENCODER_B_CHANNEL,
//					false, Encoder.EncodingType.k4X);

			upperLimit = new DigitalInput(DPIO.CUBE_LIFT_TOP_LIMIT);
//			lowerLimit = new DigitalInput(DPIO.CUBE_LIFT_BOTTOM_LIMIT);
		} else {
			System.out.println("RobotMap: Claw disabled");
		}
	}

	private static void initAscent() {
		if (!Preferences.getInstance().getBoolean("initAscent", false)) {
			System.out.println("RobotMap: initializing Ascent");
			robotAscentMotor = new WPI_TalonSRX(CAN.ROBOT_ASCENT_MOTOR);
		} else {
			System.out.println("RobotMap: Ascent disabled");
		}
	}
}
