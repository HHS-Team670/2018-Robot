package org.usfirst.frc.team670.robot.commands.actions;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Deploy extends Command {

	private boolean isDeploy;
	
	/*
	 * @param isDeploy true if it is the deploy, false if it is to pick up
	 */
    public Deploy(boolean isDeploy) {
    	this.isDeploy = isDeploy;
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.deploySupport(isDeploy);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
