package org.usfirst.frc.team670.robot.commands.drive;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Takes in a distance in inches and picks which pivot command to use
 * 
 * @author shaylan
 */
public class Drive extends CommandGroup{
	/**
	 * @param distance Distance in inches
	 */
	public Drive(double distance) {

		Robot.sensors.areEncodersWorking(true);
		addSequential(new Encoders_Drive(distance));
	}
}