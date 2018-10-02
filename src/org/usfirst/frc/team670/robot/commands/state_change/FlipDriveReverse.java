package org.usfirst.frc.team670.robot.commands.state_change;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class FlipDriveReverse extends InstantCommand {

    public FlipDriveReverse() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
    		Robot.driveBase.setIsReversed(!Robot.driveBase.isReversed());
    }

}
