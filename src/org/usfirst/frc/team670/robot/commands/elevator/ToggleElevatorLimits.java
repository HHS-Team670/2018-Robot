package org.usfirst.frc.team670.robot.commands.elevator;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ToggleElevatorLimits extends InstantCommand {

    public ToggleElevatorLimits() {
        super();
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.elevator.toggleSoftLimits(!Robot.elevator.getSoftLimits());
    }

}
