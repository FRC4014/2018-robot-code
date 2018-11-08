package org.usfirst.frc4014.powerup.vision;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PixyCam extends Subsystem{
	public PixyI2C pixy;
	Port port = Port.kOnboard;
	String print;
	public PixyPacket[] packet1 = new PixyPacket[7];
	
	public PixyCam() {
		pixy = new PixyI2C("main", new I2C(port, 0x54),packet1,new PixyException(print), new PixyPacket());
	}
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new PrintLook(this));
	}
	
	public void testPixy() {
		for (int i=1; i < 8; i++) {
			if (packet1[i-1] == null) {
				System.out.println("id"+i+"is reporting null");
			} else {
				System.out.println("pixy x value for id "+i+": " + packet1[i-1].X);
				System.out.println("pixy y value for id "+i+": " + packet1[i-1].Y);
				System.out.println("pixy width value for id "+i+": " + packet1[i-1].Width);
				System.out.println("pixy height value for id "+i+": " + packet1[i-1].Height);
			}
		} 
	}

}
