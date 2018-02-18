package org.usfirst.frc.team670.robot.commands.state_change;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class Toggle_HardSoft extends InstantCommand {

	private boolean isHard;
	
    public Toggle_HardSoft(boolean isHard) {
        super();
    	this.isHard = isHard;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.intake.setHard(isHard);
    }

}
