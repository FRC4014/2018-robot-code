package org.usfirst.frc4014.powerup.drivetrain;

import org.usfirst.frc4014.powerup.OI;
import org.usfirst.frc4014.powerup.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
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
    
    public void drive(Joystick joystick) {
    	RobotMap.driveTrainDifferentialDrive.arcadeDrive(joystick.getY(), joystick.getTwist());
    }
}
