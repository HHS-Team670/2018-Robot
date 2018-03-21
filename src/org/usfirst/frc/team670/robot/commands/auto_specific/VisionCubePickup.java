package org.usfirst.frc.team670.robot.commands.auto_specific;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.drive.IR_Drive;
import org.usfirst.frc.team670.robot.commands.drive.Pivot;
import org.usfirst.frc.team670.robot.commands.intake.OpenIntake;
import org.usfirst.frc.team670.robot.commands.intake.SpinIntake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class VisionCubePickup extends Command {

	private CommandGroup com;
	
    public VisionCubePickup() {
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	com = new CommandGroup();
    	double angle = Robot.sensors.getAngleToCube();
    	com.addParallel(new SpinIntake(-0.5, 10));
    	com.addParallel(new OpenIntake(true));
		com.addSequential(new Pivot(angle));
		com.addSequential(new IR_Drive());
    	com.addSequential(new OpenIntake(false));
		com.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
