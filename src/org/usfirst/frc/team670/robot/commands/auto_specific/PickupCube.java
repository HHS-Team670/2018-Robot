package org.usfirst.frc.team670.robot.commands.auto_specific;

import java.util.HashMap;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.LoggingCommand;
import org.usfirst.frc.team670.robot.constants.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;


/**
 * Picks up the cube if the Elevator is at the Exchange position
 */
public class PickupCube extends LoggingCommand {
	
	private double intakeSpeed = -0.5, speed = 0.2;
	private double startTime;
	
	public PickupCube() {
		requires(Robot.driveBase);
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		startTime = 0;
		logInitialize(new HashMap<String, Object>() {{
		}});
		setTimeout(0.3);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		Robot.intake.driveIntake(intakeSpeed);
		
		if(this.timeSinceInitialized() - startTime > 0.25) {
			Robot.intake.deployGrabber(false);
		}
		
		logExecute(new HashMap<String, Object>() {{
        		put("StartTime", startTime);
		}});	    	
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.intake.driveIntake(0);
		Robot.intake.deployGrabber(false);
		logFinished(new HashMap<String, Object>() {{
		}});
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}