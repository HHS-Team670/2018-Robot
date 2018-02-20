package org.usfirst.frc.team670.robot.commands.elevator;

import java.util.HashMap;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.LoggingCommand;
import org.usfirst.frc.team670.robot.constants.RoboConstants;
import org.usfirst.frc.team670.robot.constants.enums.ElevatorState;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * Uses a PID control loop plus the navX getDisplacement to move a given
 * distance in feet
 * 
 * @author vsharma8363
 *
 */
public class Encoders_Elevator extends LoggingCommand {

	private double targetPulseHeight, speed;
	private boolean isGoingUp;
	
	public Encoders_Elevator(ElevatorState state) {
		requires(Robot.elevator);
		if (state == ElevatorState.EXCHANGE)
			targetPulseHeight = RoboConstants.ELEVATOR_PULSE_FOR_EXCHANGE;
		else if (state.equals(ElevatorState.SWITCH))
			//Caleb was here

			targetPulseHeight = RoboConstants.ELEVATOR_PULSE_FOR_SWITCH;
		else if (state.equals(ElevatorState.HIGHSCALE))
			targetPulseHeight = RoboConstants.ELEVATOR_PULSE_FOR_HIGHSCALE;
		else if (state.equals(ElevatorState.EXCHANGE))
			targetPulseHeight = RoboConstants.ELEVATOR_PULSE_FOR_EXCHANGE;
		else if (state.equals(ElevatorState.MIDSCALE))
			targetPulseHeight = RoboConstants.ELEVATOR_PULSE_FOR_MIDSCALE;
		else
			targetPulseHeight = RoboConstants.ELEVATOR_PULSE_FOR_EXCHANGE;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		isGoingUp = (targetPulseHeight <= Robot.elevator.getCurrentPosition());
		logInitialize(new HashMap<String, Object>() {{
			put("Speed", speed);
			put("TargetPulseHeight", targetPulseHeight);
			put("IsGoingUp", isGoingUp);
		}});
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("Target ticks", targetPulseHeight);
		SmartDashboard.putBoolean("Is Going up:", isGoingUp);
		//Caleb was here
		if(isGoingUp)
		{
			speed = -Robot.elevator.calculateSpeed((int) Robot.elevator.getCurrentPosition(), 0.5, isGoingUp);
			speed = -0.3;
		}
		else
		{
			speed = Robot.elevator.calculateSpeed((int) Robot.elevator.getCurrentPosition(), 0.5, isGoingUp);
			speed = 0.3;
		}

		SmartDashboard.putNumber("Speed", speed);
		
		Robot.elevator.moveElevator(speed);
		
		logExecute(new HashMap<String, Object>() {{
			put("Speed", speed);
			put("IsGoingUp", isGoingUp);
		}});
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		boolean isFinished = (isGoingUp && Robot.elevator.getCurrentPosition() <= targetPulseHeight)
				|| (!isGoingUp && Robot.elevator.getCurrentPosition() >= targetPulseHeight);
		return isFinished;

	}

	// Called once after isFinished returns true
	protected void end() {
		//Ben too
		Robot.elevator.moveElevator(0);
		
		logFinished(new HashMap<String, Object>() {{
			put("Speed", speed);
			put("IsGoingUp", isGoingUp);
		}});
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

}