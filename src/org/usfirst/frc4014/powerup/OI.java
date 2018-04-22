package org.usfirst.frc4014.powerup;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    public Joystick driverJoystick;
    public JoystickButton gearRatioButton;
    public Joystick mateJoystick;
    public JoystickButton clawButton;
    public JoystickButton wheelClawReleaseButton;
    public JoystickButton wheelClawSoftReleaseButton;
    public JoystickButton wheelClawManualIntake;
    public JoystickButton raiseLiftButton;
    public JoystickButton lowerLiftButton;

    public OI() {
        initDriverJoystick();
        initMateJoystick();
    }

	private void initMateJoystick() {
		mateJoystick = new Joystick(1);
        clawButton = new JoystickButton(mateJoystick, 1);
        wheelClawReleaseButton = new JoystickButton(mateJoystick, 2);
        wheelClawSoftReleaseButton = new JoystickButton(mateJoystick, 3);
        wheelClawManualIntake = new JoystickButton(mateJoystick,4);
        raiseLiftButton = new JoystickButton(mateJoystick, 11);
        lowerLiftButton = new JoystickButton(mateJoystick, 12);
	}

	private void initDriverJoystick() {
		driverJoystick = new Joystick(0);
        gearRatioButton = new JoystickButton(driverJoystick, 2);
	}

    public Joystick getDriverJoystick() {
        return driverJoystick;
    }
}

