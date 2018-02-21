package org.usfirst.frc4014.powerup.clawlift;

import org.usfirst.frc4014.powerup.OI;

import edu.wpi.first.wpilibj.command.Command;

public class AscendClawByJoystick extends Command {

    private final ClawLift claw;
    private final OI oi;

    public AscendClawByJoystick(ClawLift claw, OI oi) {
        super();
        this.oi = oi;
        this.claw = claw;
        requires(claw);
    }

    protected void execute() {
        if (claw.enableMotor) {
            claw.ascend(oi.mateJoystick);
        }
    }

    @Override
    protected boolean isFinished() {
        // we're always driving, so this is always false
        return false;
    }

}
