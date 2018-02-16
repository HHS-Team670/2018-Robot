package org.usfirst.frc.team670.robot.commands.drive;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


/**
 * Takes in a degree value and picks which pivot command to use
 * 
 * @author shaylan
 */
public class Pivot extends Command{

	private double degrees;
	private Command com;

	/**
	 * @param degrees Angle in degrees
	 */
	public Pivot(double degrees) {
		requires(Robot.driveBase);
		this.degrees = degrees;

		if(Robot.sensors.isNavXConnected())
			com = new NavX_Pivot(degrees);
		else {
			com = new Encoders_Pivot(degrees);
			Robot.sensors.areEncodersWorking(true);
		}
		com.start();
	}

	@Override
	protected boolean isFinished() {
		return com.isCompleted();
	}

}