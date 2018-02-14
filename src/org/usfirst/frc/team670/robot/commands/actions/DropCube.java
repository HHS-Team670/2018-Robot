package org.usfirst.frc.team670.robot.commands.actions;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DropCube extends Command {

	private CommandGroup com;
	
    public DropCube() {
        // Use requires() here to declare subsystem dependencies
    	com = new CommandGroup();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	com.addParallel(new Intake(-0.8, 10));
    	com.addSequential(new Delay(1.5));
    	com.addSequential(new Grab(false));
    	com.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(Robot.sensors.getDistanceIntakeInches() >= 2 || com.isCompleted())
        	return true;
        else
        	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	com.cancel();
    	new Grab(false).start();
    	Robot.intake.driveIntake(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
