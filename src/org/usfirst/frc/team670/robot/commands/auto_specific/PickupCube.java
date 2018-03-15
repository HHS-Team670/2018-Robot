package org.usfirst.frc.team670.robot.commands.auto_specific;

import java.util.HashMap;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.LoggingCommand;
import org.usfirst.frc.team670.robot.constants.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;


/**
 *
 */
public class PickupCube extends LoggingCommand {
	
	private DigitalInput limitSwitch;
	private double intakeSpeed = -0.5, speed = 0.2;
	
	public PickupCube() {
		limitSwitch = new DigitalInput(RobotMap.intakeLimit);
		requires(Robot.driveBase);
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		logInitialize(new HashMap<String, Object>() {{
		}});
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveBase.drive(speed, speed);
		Robot.intake.driveIntake(intakeSpeed);
		logExecute(new HashMap<String, Object>() {{
		}});	    	
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return limitSwitch.get();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveBase.drive(0,0);
		Robot.intake.driveIntake(0);
		logFinished(new HashMap<String, Object>() {{
		}});
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}