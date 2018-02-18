package org.usfirst.frc.team670.robot.commands.auto_specific;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Vision_PowerCube extends Command {

	private double speed;
	/**
	 * @param speed Maximum driving speed for the robot (Must be between 0.2 and 0.5)
	 */
    public Vision_PowerCube(double speed) {
    	this.speed = speed;
    	requires(Robot.driveBase);
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angle = Robot.sensors.getAngle();
    	double shift = (angle/30);
    	if(shift > 1.0)
    		shift = 1.0;
    	if(angle > 0) //turn right
    		Robot.driveBase.drive(speed-(speed*shift), speed);
    	else //turn left
    		Robot.driveBase.drive(speed, speed-(speed*shift));
    	
    	if(Robot.sensors.getDistanceIntakeInches() <= 10)
        	Robot.intake.deployGrabber(true);
    	
    	Robot.intake.driveIntake(0.75);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.deployGrabber(true);
    	Robot.intake.driveIntake(0);
    	Robot.driveBase.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
