package org.usfirst.frc.team670.robot.commands.auto_specific;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Delay extends Command {
	  private double seconds = 0;
	    
	    public Delay(double seconds) {
	        this.seconds = seconds;
	       // requires(Robot.driveBase);
	    }

	    // Called just before this Command runs the first time
	    protected void initialize() {
	        setTimeout(seconds);
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	//Drive seven feet to baseline
	    	
	    }

	    // Make this return true when this Command no longer needs to run execute()
	    protected boolean isFinished() {
	        return isTimedOut();
	    }

	    // Called once after isFinished returns true
	    protected void end() {
	    }

	    // Called when another command which requires one or more of the same
	    // subsystems is scheduled to run
	    protected void interrupted() {
	    }
	}