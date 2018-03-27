package org.usfirst.frc4014.powerup.wheeledclaw.wclaw;

import org.usfirst.frc4014.powerup.OI;
import org.usfirst.frc4014.powerup.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WheeledClaw extends Subsystem{

	private OI oi;
	private double clawSpeed = 1;
	
	public WheeledClaw(OI oi) {
		this.oi = oi;
		oi.clawButton.whenPressed(new IntakeCube(this, oi));
		oi.wheelClawReleaseButton.whenPressed(new OutputCube(this));
		oi.wheelClawSoftReleaseButton.whenPressed(new SoftOutput(this));
//		oi.clawButton.whenPressed(new ClampClaw(this));
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO: do we need a default command here?
	}
	
	protected void intake() {
	    System.out.println("attempting intake");
		RobotMap.clawMotorA.set(-clawSpeed);
		RobotMap.clawMotorB.set(-clawSpeed);
	}
	
	protected void output() {
	    System.out.println("attempting output");
		RobotMap.clawMotorA.set(clawSpeed);
		RobotMap.clawMotorB.set(clawSpeed);
	}
	
	protected void slowOutput() {
		RobotMap.clawMotorA.set(clawSpeed * .5);
		RobotMap.clawMotorB.set(clawSpeed * .5);
	}
	
	protected void hold() {
	    RobotMap.clawMotorA.set(0);
	    RobotMap.clawMotorB.set(0);
	}
	
	//opens so the claw is ready to grab a power cube
		protected void open() {
			RobotMap.clawSolenoidA.set(DoubleSolenoid.Value.kReverse);
			SmartDashboard.putBoolean("Pneumatics are closed", false);
		}
		
		//closes to grab the power cube
		protected void close() {
			RobotMap.clawSolenoidA.set(DoubleSolenoid.Value.kForward);
	         SmartDashboard.putBoolean("Pneumatics are closed", true);
		}

}
