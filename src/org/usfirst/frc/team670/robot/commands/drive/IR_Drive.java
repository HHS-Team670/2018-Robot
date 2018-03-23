package org.usfirst.frc.team670.robot.commands.drive;

import java.util.HashMap;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.LoggingCommand;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IR_Drive extends LoggingCommand {

	private final double speed;

	public IR_Drive() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		speed = 0.25;
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.intake.deployGrabber(true);
		logInitialize(new HashMap<String, Object>() {{
		}});
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveBase.drive(speed, speed);
		Robot.intake.driveIntake(-0.5);
		logExecute(new HashMap<String, Object>() {{
			put("Speed", speed);
		}});
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.sensors.isCubeInIntake();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveBase.drive(0, 0);
		logFinished(new HashMap<String, Object>() {{
		}});
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
