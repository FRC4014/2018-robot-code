package wclaw;

import org.usfirst.frc4014.powerup.OI;
import org.usfirst.frc4014.powerup.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class WheeledClaw extends Subsystem{

	private OI oi;
	private double clawSpeed = 1;
	
	public WheeledClaw(OI oi) {
		this.oi = oi;
		oi.clawButton.toggleWhenPressed(new IntakeCube(this));
		oi.wheelClawReleaseButton.whenPressed(new OutputCube(this));
//		oi.clawButton.whenPressed(new ClampClaw(this));
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	protected void intake() {
		RobotMap.clawMotorA.set(clawSpeed);
		RobotMap.clawMotorB.set(-clawSpeed);
	}
	
	protected void output() {
		RobotMap.clawMotorA.set(-clawSpeed);
		RobotMap.clawMotorB.set(clawSpeed);
	}
	
	//opens so the claw is ready to grab a power cube
		protected void open() {
			RobotMap.clawSolenoidA.set(DoubleSolenoid.Value.kReverse);
//			RobotMap.clawSolenoidB.set(DoubleSolenoid.Value.kReverse);
		}
		
		//closes to grab the power cube
		protected void close() {
			RobotMap.clawSolenoidA.set(DoubleSolenoid.Value.kForward);
//			RobotMap.clawSolenoidB.set(DoubleSolenoid.Value.kForward);
		}

}
