package org.usfirst.frc4014.powerup.autonomous;

import edu.wpi.first.wpilibj.command.Command;

public class TrackTimeCommand extends Command {

    private final boolean initializeTimer;

    static final class TimeTracker {
        final String name;
        long initialTime;

        TimeTracker(String trackerName) {
            name = trackerName;
        }
        void init() {
            initialTime = System.currentTimeMillis();
        }
    }

    final TimeTracker tracker;

    TrackTimeCommand(TimeTracker timeTracker, boolean initializeTimer) {
        this.tracker = timeTracker;
        this.initializeTimer = initializeTimer;
    }

    @Override
    protected void initialize() {
        if (initializeTimer){
            tracker.init();
        }
    }

    @Override
    protected boolean isFinished() {
        System.out.println("TimeTracker '" + tracker.name + "' millis elapsed " +
                (System.currentTimeMillis() - tracker.initialTime));
        return true;
    }
}
