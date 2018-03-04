package org.usfirst.frc4014.powerup.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.IllegalUseOfCommandException;

public class RobotPositionCommand extends Command{

    public enum Position {FarLeft, Left, Center, Right, FarRight};

    private final Position position;

    public RobotPositionCommand(Position p) {
        this.position = p;
    }

    @Override
    protected boolean isFinished() {
        throw new IllegalUseOfCommandException(
                "RobotPositionCommand should not have been run. It's meant only for robot position.");
    }

    public Position getPosition() {
        return position;
    }
}
