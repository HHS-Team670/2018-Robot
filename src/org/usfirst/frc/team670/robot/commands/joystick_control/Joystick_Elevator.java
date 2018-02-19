package org.usfirst.frc.team670.robot.commands.joystick_control;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.constants.RoboConstants;
import org.usfirst.frc.team670.robot.constants.enums.OperatorState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Joystick_Elevator extends Command {

	public Joystick_Elevator() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.elevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Robot.elevator.resetEncoder();
		// Robot.elevator.toggleSoftLimits(true);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double speed = Robot.elevator.calculateSpeed((int)Robot.elevator.getCurrentPosition(), Robot.oi.getOperatorStick().getY());
		if (Robot.oi.getOS().equals(OperatorState.ELEVATOR))
			Robot.elevator.moveElevator(speed);
		else
			Robot.elevator.moveElevator(0);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevator.moveElevator(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.elevator.moveElevator(0);
	}
}