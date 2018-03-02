package org.usfirst.frc.team670.robot.commands.state_change;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SwitchCams extends InstantCommand {

    public SwitchCams() {
        super();
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.switchCameras();
    }

}
