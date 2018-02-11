package org.usfirst.frc.team670.robot.commands.actions.components;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.constants.DriverState;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Flips controls for the driver when called
 * 
 * @author vsharma
 */
public class Set_DriverControl extends Command {
	
	private DriverState ds;
	
    public Set_DriverControl(DriverState ds) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.ds = ds;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.oi.setDriverState(ds);
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