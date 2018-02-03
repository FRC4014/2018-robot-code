package org.usfirst.frc4014.powerup.drivetrain;

import org.usfirst.frc4014.powerup.OI;
import org.usfirst.frc4014.powerup.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


/**
 *
 */
public class DriveTrain extends Subsystem {

	private OI oi;

    public DriveTrain(OI oi) {
		this.oi = oi;
		
	}

	@Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
         setDefaultCommand(new DriveByJoystick(this, oi));
    }
	
	public void slowGearRatio() {
		RobotMap.driveTrainSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void fastGearRatio() {
		RobotMap.driveTrainSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
    
    public void drive(Joystick joystick) {
    	RobotMap.driveTrainDifferentialDrive.arcadeDrive(joystick.getY(), joystick.getTwist());
    }
    
    public void drive(double speed) {
    	RobotMap.driveTrainDifferentialDrive.arcadeDrive(-speed, 0);
    }
    
    public void rotate(double turnSpeed) {
    	RobotMap.driveTrainDifferentialDrive.arcadeDrive(0, turnSpeed);
    }

	public void resetEncoders() {
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();
	}
	
	public double getRightWheelDistance() {
		// TODO: this is just a placeholder ... need to calculate distance based on pulses or something
		return (RobotMap.rightEncoder.getDistance());// * DISTANCE_PER_PULSE;
	}

	public void setNeutralMode(NeutralMode mode) {
		RobotMap.driveTrainLeftMotorA.setNeutralMode(mode);
		RobotMap.driveTrainLeftMotorB.setNeutralMode(mode);
		RobotMap.driveTrainRightMotorA.setNeutralMode(mode);
		RobotMap.driveTrainRightMotorB.setNeutralMode(mode);
	}
}
