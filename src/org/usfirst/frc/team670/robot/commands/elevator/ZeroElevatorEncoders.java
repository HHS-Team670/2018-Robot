package org.usfirst.frc.team670.robot.commands.elevator;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *Allows us to move the elevator position to the bottom of the elevator and reset encoder ticks to zero
 */
public class ZeroElevatorEncoders extends Command {
	
	public ZeroElevatorEncoders() {
		requires(Robot.elevator);
	}
	
	protected void initialize() {
	}

	protected void execute() {
		Robot.elevator.moveElevator(+0.25);
	}

	protected boolean isFinished() {
		if(Math.abs(Robot.elevator.getVelocity()) <= 0.05)
			return true;
		else
			return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevator.resetEncoder();
		Robot.elevator.moveElevator(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.elevator.moveElevator(0);
	}
}
