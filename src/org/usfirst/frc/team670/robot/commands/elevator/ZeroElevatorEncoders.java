package org.usfirst.frc.team670.robot.commands.elevator;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *Allows us to move the elevator position to the bottom of the elevator and reset encoder ticks to zero
 */
public class ZeroElevatorEncoders extends Command {

	//	private double[] pastEncoderVals;

	public ZeroElevatorEncoders() {
		requires(Robot.elevator);
		//        pastEncoderVals = new double[3];
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.elevator.toggleSoftLimits(false);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.elevator.moveElevator(-0.5);
		//    	for(int i = 1; i < pastEncoderVals.length; i++) {
		//    		pastEncoderVals[i-1] = pastEncoderVals[i];
		//    	}
		//    	pastEncoderVals[pastEncoderVals.length-1] = Robot.elevator.getTalon().getSensorCollection().getPulseWidthPosition();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		//    	for(int i = 1; i < pastEncoderVals.length; i++) {
		//    		if(pastEncoderVals[i-1] != pastEncoderVals[i]) {
		//    			return false;
		//    		}
		//    	}
		if(Robot.elevator.getTalon().getSensorCollection().isRevLimitSwitchClosed())
			return false;
		else
			return true;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevator.resetEncoder();
		//Robot.elevator.toggleSoftLimits(true);
		Robot.elevator.moveElevator(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.elevator.moveElevator(0);
	}
}
