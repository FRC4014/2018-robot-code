package org.usfirst.frc4014.powerup.drivetrain;

import org.usfirst.frc4014.powerup.OI;
import org.usfirst.frc4014.powerup.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

    private OI oi;
    private double integral = 0;
    private double prevError = 0;
    private double dtP, dtI, dtD = 0;
    private double turnValue;

    private boolean gearRatioIsHigh = false;

    public DriveTrain(OI oi) {
        this.oi = oi;
        oi.gearRatioButton.toggleWhenPressed(new ToggleGearRatio(this));
        dtP = Preferences.getInstance().getDouble("dtP", 0.5);
        dtI = Preferences.getInstance().getDouble("dtI", 0);
        dtD = Preferences.getInstance().getDouble("dtD", 0);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveByJoystick(this, oi));
    }

    public void slowGearRatio() {
        System.out.println("///////////////////////////////////////////// switched ratio to low");
//         RobotMap.driveTrainSolenoidA.set(DoubleSolenoid.Value.kReverse);
//         RobotMap.driveTrainSolenoidB.set(DoubleSolenoid.Value.kReverse);
        gearRatioIsHigh = false;
    }

    public void fastGearRatio() {
        System.out.println("///////////////////////////////////////////// switched ratio to high");
//         RobotMap.driveTrainSolenoidA.set(DoubleSolenoid.Value.kForward);
//         RobotMap.driveTrainSolenoidB.set(DoubleSolenoid.Value.kForward);
        gearRatioIsHigh = true;
    }

    public void drive(Joystick joystick) {
        if (Math.abs(joystick.getY()) > .1) {
            turnValue = -joystick.getTwist() + Preferences.getInstance().getDouble("dtFudgeValue", 0);
        } else {
            turnValue = -joystick.getTwist();
        }
        RobotMap.driveTrainDifferentialDrive.arcadeDrive(joystick.getY(), joystick.getTwist());
        double velocity = Math.abs(RobotMap.leftEncoder.getRate());
        double ldist = RobotMap.leftEncoder.getRaw();
        double rdist = RobotMap.rightEncoder.getRaw();
        if (velocity > Preferences.getInstance().getDouble("speed up theshold", 100) && !gearRatioIsHigh) {
            fastGearRatio();

        } else if (velocity < Preferences.getInstance().getDouble("speed down threshold", 90) && gearRatioIsHigh) {
            slowGearRatio();

        }
//        System.out.println("Velocity is: " + velocity + " | left dist = " + ldist + " | right dist = " + rdist);
    }

    public void resetEncoders() {
        RobotMap.leftEncoder.reset();
        RobotMap.rightEncoder.reset();
    }

    public void setNeutralMode(NeutralMode mode) {
        RobotMap.driveTrainLeftMotorA.setNeutralMode(mode);
        RobotMap.driveTrainLeftMotorB.setNeutralMode(mode);
        RobotMap.driveTrainRightMotorA.setNeutralMode(mode);
        RobotMap.driveTrainRightMotorB.setNeutralMode(mode);
    }

    // autonomous should always use squaredInputs=false, Differential defaults to
    // true which caused the pivot command fail
    public void arcadeDrive(double xSpeed, double zRotation, boolean squaredInputs) {
        RobotMap.driveTrainDifferentialDrive.arcadeDrive(-xSpeed, -zRotation, squaredInputs);
    }
}
