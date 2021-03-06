package org.usfirst.frc4014.powerup;

/**
 * Logical names for CAN IDs.
 * Use the RoboRIO System Configuration tool so
 * the robot/hotbench match these settings IE. 
 */
public final class CAN {

	// Change these in the RoboRIO System Configuration tool, not in this code.
	public static final int ROBOT_ASCENT_MOTOR = 5;
	public static final int DRIVE_TRAIN_LEFT_MOTOR_A = 4;
	public static final int DRIVE_TRAIN_LEFT_MOTOR_B = 3;
	public static final int DRIVE_TRAIN_RIGHT_MOTOR_A = 2;
	public static final int DRIVE_TRAIN_RIGHT_MOTOR_B = 1;
	public static final int CLAW_MOTOR_A = 6;
	public static final int CLAW_MOTOR_B = 7;
	public static final int CLAW_ASCENT_MOTOR_A = 9;
	public static final int CLAW_ASCENT_MOTOR_B = 9;

	/**
	 * This class is only used for static references, so hide the constructor.
	 */
	private CAN() {
	}
}
