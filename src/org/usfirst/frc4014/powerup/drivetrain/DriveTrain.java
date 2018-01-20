package org.usfirst.frc4014.powerup.drivetrain;

import org.usfirst.frc4014.powerup.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


/**
 *
 */
public class DriveTrain extends Subsystem {

    private final WPI_TalonSRX leftMotor = RobotMap.driveTrainLeftMotor;
    private final WPI_TalonSRX rightMotor = RobotMap.driveTrainRightMotor;
    private final DifferentialDrive differentialDrive = RobotMap.driveTrainDifferentialDrive;

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}
