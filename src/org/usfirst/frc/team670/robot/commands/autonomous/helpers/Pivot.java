package org.usfirst.frc.team670.robot.commands.autonomous.helpers;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Pivot extends Command {

	private double finalAngle, startAngle, angle;
	private double percentComplete;
	
	/**
	 * 
	 * 
	 * @param angle The angle to turn, positive is right, negative is left.
	 */
	public Pivot(double angle) {
		this.angle = angle;
		requires(Robot.driveBase);
		// Use requires() here to declare subsystem dependencies
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.sensors.zeroYaw();
		this.finalAngle = startAngle + angle;
		System.out.println("started: Pivot");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//Percent of the turn left
		//double speed = (0.8) - ((2.8)*(Math.pow(percent-0.5, 2)));

		double currentAngle = Robot.sensors.getYaw();
		double speed = 0;
		percentComplete = Math.abs(currentAngle/angle);
		
		if(percentComplete < 0.5) {
			speed = percentComplete * 2 + 0.1;
		}
		else {
			speed = 1 - (percentComplete - 0.5) + 0.1;
		}
		
//		double percentComplete = Math.abs((Math.abs(finalAngle) - Math.abs(currentAngle))/angle);
//		if(percentComplete < .5)
//			speed = percentComplete * 2 + 0.09;
//		else
//			speed = 1 - percentComplete + 0.09;

		//    	double speed = 0.1;
		if(angle > 0)
			Robot.driveBase.drive(-speed, speed);
		else
			Robot.driveBase.drive(speed, -speed);
		
		/*System.out.println("Start: " + startAngle);
		System.out.println("Yaw: " + Robot.sensors.getYaw());
		System.out.println(percentComplete);*/
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(percentComplete >= 1)
		{
			System.out.println("finished");
			return true;
		}
		else 
		{
			return false;
		}

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