package org.usfirst.frc.team670.robot.commands.auto_specific;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpinIntake extends Command {

	private double speed, seconds;
	
    public SpinIntake(double speed, double seconds) {
    	this.speed = speed;
    	this.seconds = seconds;
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        setTimeout(seconds);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.driveIntake(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.driveIntake(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intake.driveIntake(0);
    }
}
