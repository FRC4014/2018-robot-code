package org.usfirst.frc4014.powerup.wheeledclaw.wclaw;

import edu.wpi.first.wpilibj.command.Command;

public class SimpleIntake extends Command{

    private final WheeledClaw wheeledClaw;
    private long initTimestamp;
    
    public SimpleIntake(WheeledClaw wheeledClaw) {
        this.wheeledClaw = wheeledClaw;
    }
    
    protected void initialize() {
        initTimestamp = System.currentTimeMillis();
    }
    
    protected void execute () {
        wheeledClaw.intake();
    }
    
    protected void end() {
        wheeledClaw.hold();
    }
    
    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return (System.currentTimeMillis() - initTimestamp > 200);
    }

}
