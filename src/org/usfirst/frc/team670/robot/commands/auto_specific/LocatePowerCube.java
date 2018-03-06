package org.usfirst.frc.team670.robot.commands.auto_specific;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LocatePowerCube extends Command {
	
	private double speed = 0.2, lspeed = 0, rspeed = 0, tolerance = 2.5, startTime = 0;
	
    public LocatePowerCube() {
        requires(Robot.driveBase);
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angle = Robot.sensors.getAngleToCube();
    	
    	//Need to turn left
    	if(angle < -tolerance)
    	{
    		lspeed = speed*0.75;
    		rspeed = speed*1.25;
    	}
    	//Need to turn right
    	else if(angle > tolerance)
    	{
    		lspeed = speed*1.25;
    		rspeed = speed*0.75;
    	}
    	//You need to go straight
    	else
    	{
    		lspeed = speed;
    		rspeed = speed;
    	}
    	
		Robot.intake.deployGrabber(false);
		
    	//Open and close the intake to ensure the cube is in correct every few seconds
    	if(((System.currentTimeMillis() - startTime)/1000.0)%2.0 < 0.5)
    		Robot.intake.deployGrabber(true);
    	
    	Robot.intake.driveIntake(-speed * 1.25);
    	Robot.driveBase.drive(lspeed, rspeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       return Robot.sensors.isCubeInIntake();
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.intake.deployGrabber(false);
    	Robot.driveBase.drive(0, 0);
    	Robot.intake.driveIntake(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
