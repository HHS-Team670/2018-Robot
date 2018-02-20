package org.usfirst.frc.team670.robot.commands.joystick_control;

import java.util.HashMap;

import org.usfirst.frc.team670.robot.Robot;
<<<<<<< HEAD
import edu.wpi.first.wpilibj.command.Command;
=======
import org.usfirst.frc.team670.robot.commands.LoggingCommand;

public class Joystick_Drive extends LoggingCommand {
>>>>>>> Logging

	private double opL, opR, d = 0.05;
<<<<<<< HEAD
	
=======
	private double left, right;

>>>>>>> Logging
	public Joystick_Drive() {
		requires(Robot.driveBase);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveBase.initJoystickDrive();
		left = 0;
		right = 0;
		logInitialize(new HashMap<String, Object>() {{
		}});
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
<<<<<<< HEAD
		double left = Robot.oi.getLeftStick().getY();
		double right = Robot.oi.getRightStick().getY();
		/*if(right > opR+right)
=======
		left = Robot.oi.getLeftStick().getY();
		right = Robot.oi.getRightStick().getY();
		if(right > opR+right)
>>>>>>> Logging
			right = opR+d;
		else if(right < opR-d)
			right = opR-d;
		opR = right;

		if(left > opL+left)
			left = opL+d;
		else if(left < opL-d)
			left = opL-d;
<<<<<<< HEAD
		opL = left;*/
		
=======
		opL = left;

>>>>>>> Logging
		Robot.driveBase.drive(left, right);

		logExecute(new HashMap<String, Object>() {{
			put("LeftStickPos", Robot.oi.getLeftStick().getY());
			put("RightStickPos", Robot.oi.getRightStick().getY());
			put("LeftMotorInput", left);
			put("RightMotorInput", right);
		}});
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveBase.drive(0, 0);
		logFinished(new HashMap<String, Object>() {{
		}});
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}