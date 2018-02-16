package org.usfirst.frc.team670.robot.commands.elevator;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.auto_specific.Delay;
import org.usfirst.frc.team670.robot.enums.ElevatorState;

import edu.wpi.first.wpilibj.command.Command;


/**
 * Takes in a distance in inches and picks which pivot command to use
 * 
 * @author shaylan
 */
public class Elevate extends Command{

	private ElevatorState state;
	private Command com;

	/**
	 * @param degrees Distance in inches
	 */
	public Elevate(ElevatorState state) {
		requires(Robot.elevator);
		this.state = state;

		if(Robot.elevator.getTalon().getSensorCollection() != null) {
			com = new Encoders_Elevator(state);
			Robot.sensors.areElevatorEncodersWorking(true);
		}
		else {
			com = new Delay(4);
			Robot.sensors.areElevatorEncodersWorking(false);
		}
		com.start();
	}

	@Override
	protected boolean isFinished() {
		return !com.isRunning();
	}

}