package org.usfirst.frc.team670.robot.commands.joystick_control;

import org.usfirst.frc.team670.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Joystick_Drive extends Command {
	
	private double opL, opR, d = 0.05;
	
	public Joystick_Drive() {
		requires(Robot.driveBase);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveBase.initJoystickDrive();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double left = Robot.oi.getLeftStick().getY();
		double right = Robot.oi.getRightStick().getY();
		/*if(right > opR+right)
			right = opR+d;
		else if(right < opR-d)
			right = opR-d;
		opR = right;
		
		if(left > opL+left)
			left = opL+d;
		else if(left < opL-d)
			left = opL-d;
		opL = left;*/
		
		Robot.driveBase.drive(left, right);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveBase.drive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.driveBase.drive(0, 0);
	}
}