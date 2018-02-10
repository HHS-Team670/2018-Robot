package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PrintElevator extends Command {
	
	
    public PrintElevator() {
    	requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.getTalon().getSensorCollection().setPulseWidthPosition(0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double y = Robot.oi.getLeftStick().getY();
    	Robot.elevator.moveElevator(y*0.1);
    	System.out.println(Robot.elevator.getTalon().getSensorCollection().getPulseWidthPosition());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
