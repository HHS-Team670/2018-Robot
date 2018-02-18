package org.usfirst.frc.team670.robot.commands.auto_specific;

<<<<<<< HEAD
import org.usfirst.frc.team670.robot.commands.intake.OpenIntake;
=======
import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.intake.CloseIntake;
>>>>>>> parent of 7485210... NAVX DOESNT WORK + GAME SPECIFIC STRING WAIT TIME

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
<<<<<<< HEAD
public class PickupCube extends CommandGroup {

    public PickupCube() {
    	setTimeout(3);
    	
    	addSequential(new OpenIntake(true));
    	addParallel(new SpinIntake(0.55, 10));
    	addSequential(new Delay(1.5));
		addParallel(new OpenIntake(false));
    }
    
    public boolean isFinished()
    {
    	return isTimedOut();
    }
=======
public class PickupCube extends Command {

	CommandGroup com;

	public PickupCube() {
		// Use requires() here to declare subsystem dependencies
		com = new CommandGroup();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		setTimeout(3);
		new CloseIntake(true).start();
		com.addParallel(new SpinIntake(0.55, 10));
		com.addSequential(new Delay(1.5));
		com.addSequential(new CloseIntake(false));
		com.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		com.cancel();
		new CloseIntake(false).start();
		Robot.intake.driveIntake(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
>>>>>>> parent of 7485210... NAVX DOESNT WORK + GAME SPECIFIC STRING WAIT TIME
}
