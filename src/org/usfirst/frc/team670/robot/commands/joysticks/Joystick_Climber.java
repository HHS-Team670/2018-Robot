package org.usfirst.frc.team670.robot.commands.joysticks;


import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.constants.OperatorState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Joystick_Climber extends Command {

	public Joystick_Climber() {
		requires(Robot.climber);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.climber.climb(0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(Robot.oi.getOS().equals(OperatorState.CLIMBER))
			Robot.climber.climb(Robot.oi.getOperatorStick().getY());
		else
			Robot.climber.climb(0);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
//		if(current >= currentLimit)
//		{
//			stopped = true;
//			working = false;
//			return true;
//		}
//		else
			return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.climber.climb(0);
	}
	
//	public static boolean isWorking()
//	{
//		return working;
//	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.climber.climb(0);
	}
}