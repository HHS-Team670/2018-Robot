package org.usfirst.frc.team670.robot.commands.state_change;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class Set_Hard_Soft extends InstantCommand {

    public Set_Hard_Soft() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.intake.setHard(!Robot.intake.is_hard);
    }

}
