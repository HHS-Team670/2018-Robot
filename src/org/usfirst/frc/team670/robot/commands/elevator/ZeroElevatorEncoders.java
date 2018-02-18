package org.usfirst.frc.team670.robot.commands.elevator;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *Allows us to move the elevator position to the bottom of the elevator and reset encoder ticks to zero
 */
public class ZeroElevatorEncoders extends Command {
	
	public ZeroElevatorEncoders() {
		requires(Robot.elevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.elevator.toggleSoftLimits(false);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.elevator.moveElevator(-0.5);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(Robot.elevator.getTalon().getMotorOutputPercent() > 0.05 && Robot.elevator.getTalon().getOutputCurrent() <= 0.05)
			return true;
		else
			return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevator.resetEncoder();
		Robot.elevator.toggleSoftLimits(true);
		Robot.elevator.moveElevator(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.elevator.moveElevator(0);
	}
}
