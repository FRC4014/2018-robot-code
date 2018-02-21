package org.usfirst.frc4014.powerup.autonomous;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4014.powerup.RobotMap;
import org.usfirst.frc4014.powerup.clawlift.ClawLift;

public class AscendClawByDistance extends Command {

    private final ClawLift clawLift;
    private final double distance;
    private double speed;
    private long initTimestamp;

    public AscendClawByDistance(ClawLift clawLift, double distanceInches) {
        this.clawLift = clawLift;
        this.distance = distanceInches;
        requires(clawLift);
    }

    @Override
    protected void initialize() {
        initTimestamp = System.currentTimeMillis();
        clawLift.resetEncoder();
        speed = Preferences.getInstance().getDouble("AutoAscendClawSpeed", 1.0);
    }

    @Override
    protected void execute() {
        clawLift.ascend((distance > 0) ? speed : -speed);
    }

    @Override
    protected boolean isFinished() {
        return probableCollision() ||
                achievedDistance();
    }

    private boolean probableCollision() {
        boolean collision = (System.currentTimeMillis() - initTimestamp > 150) &&
                (Math.abs(RobotMap.clawAscentEncoder.getRate()) < 1.0);
        if (collision) {
            System.out.println("AscendClawByDistance: collision");
        }
        return collision;
    }

    private boolean achievedDistance() {
        double encoderDistance = RobotMap.clawAscentEncoder.getDistance();
        boolean finished = encoderDistance >= distance - 0.5;
        System.out.println("AscendClawByDistance: target distance = " + distance +
                " | ENCODER distance = " + encoderDistance +
                " | speed = " + speed + " | is finished = " + finished);
        if (finished) {
            System.out.println("AscendClawByDistance: ascended " + distance);
        }
        return finished;
    }
}
