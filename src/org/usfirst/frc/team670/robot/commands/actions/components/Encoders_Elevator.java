package org.usfirst.frc.team670.robot.commands.actions.components;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.Constants;
import org.usfirst.frc.team670.robot.utilities.ElevatorState;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
/**
 * 
 * Uses a PID control loop plus the navX getDisplacement to move a given distance in feet
 * 
 * @author vsharma8363
 *
 */
public class Encoders_Elevator extends Command{
	
	private double targetPulseHeight;
	private boolean isGoingUp;
	private double speed, tolerance;
	
	public Encoders_Elevator(ElevatorState state, double speed) {
		tolerance = speed * 100;
		this.speed = speed;
		if(state == ElevatorState.EXCHANGE)
			targetPulseHeight = Constants.elevatorPulseForExchange;
		else if(state.equals(ElevatorState.SWITCH))
			targetPulseHeight = Constants.elevatorPulseForSwitch;
		else if(state.equals(ElevatorState.SCALE))
			targetPulseHeight = Constants.elevatorPulseForScale;
		else if(state.equals(ElevatorState.SCALE))
			targetPulseHeight = Constants.elevatorPulseForDown;
		else
			targetPulseHeight = Constants.elevatorPulseForExchange;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (Math.abs(targetPulseHeight) < Math.abs(Robot.elevator.getCurrentPosition()))
			isGoingUp = false;
		else
			isGoingUp = true;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(isGoingUp)
			Robot.elevator.moveElevator(speed);
		else
			Robot.elevator.moveElevator(-speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() 
	{
		double delta = Math.abs(Math.abs(targetPulseHeight) - Math.abs(Robot.elevator.getCurrentPosition()));
		if(delta < tolerance)
			return true;
		else
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
		end();
	}
}
