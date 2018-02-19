package org.usfirst.frc.team670.robot.commands.drive;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.constants.RoboConstants;

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
public class Encoders_Pivot extends Command {

	private double ticksToTravel, minVelocity = 0.05;
	private int numTimesMotorOutput;
	private boolean reachedMinSpeed;

	public Encoders_Pivot(double angle) {
		double ticksPerDegree = 4711 / 90;
		ticksToTravel = ticksPerDegree * angle;
		numTimesMotorOutput = 0;
		reachedMinSpeed = false;
		/*
		 * double circum = Ma th.PI*RoboConstants.PIVOT_RADIUS; double inches =
		 * circum*(angle/360.0); this.ticksToTravel =
		 * ((inches)/(Math.PI*RoboConstants.DRIVEBASE_WHEEL_DIAMETER)) *
		 * RoboConstants.DRIVEBASE_TICKS_PER_ROTATION;
		 */
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		numTimesMotorOutput = 0;
		Robot.driveBase.initPIDPivoting(Robot.driveBase.getLeft());
		Robot.driveBase.initPIDPivoting(Robot.driveBase.getRight());
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveBase.getLeft().set(ControlMode.Position, -ticksToTravel);
		Robot.driveBase.getRight().set(ControlMode.Position, ticksToTravel);
		reachedMinSpeed = Math.abs(Robot.driveBase.getLeft().getSensorCollection().getQuadratureVelocity()) > minVelocity;

		/* 50 rotations in either direction */
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Math.abs(Robot.driveBase.getRight().getSensorCollection().getQuadratureVelocity()) <= minVelocity
				&& Math.abs(
						Robot.driveBase.getRight().getSensorCollection().getQuadratureVelocity()) <= minVelocity
				&& reachedMinSpeed)
			numTimesMotorOutput++;
		return (numTimesMotorOutput >= 5);
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveBase.drive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}