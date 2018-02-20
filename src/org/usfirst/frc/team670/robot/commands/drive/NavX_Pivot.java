package org.usfirst.frc.team670.robot.commands.drive;

import java.util.HashMap;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.LoggingCommand;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class NavX_Pivot extends LoggingCommand {

	private double finalAngle, startAngle, angle;
	private double percentComplete;
	private int numTimesIsFinished;

	/**
	 * 
	 * 
	 * @param angle
	 *            The angle to turn, positive is right, negative is left.
	 */
	public NavX_Pivot(double angle) {
		this.angle = angle;
		numTimesIsFinished = 0;
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		this.startAngle = getYaw();
		this.finalAngle = startAngle + angle;
		this.numTimesIsFinished = 0;
		Robot.driveBase.getLeft().configOpenloopRamp(0, 0);
		Robot.driveBase.getRight().configOpenloopRamp(0, 0);
		logInitialize(new HashMap<String, Object>() {{
		    put("StartAngle", startAngle);
		    put("FinalAngle", finalAngle);
		    put("DegreesToTravel", finalAngle - startAngle);
		    put("PercentComplete", percentComplete);
		}});
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("Percent Complete (NavX)", percentComplete);
		double speed = 0;
		percentComplete = Math.abs((angle - yawRemaining()) / (angle));

		if (percentComplete <= 0.6) {
//			speed = -2.3 * 2.3 * (percentComplete * percentComplete) + 0.8;
			speed = 0.7 - percentComplete;
		} 
		else if(Math.abs(1.0-percentComplete) < 0.15){
			speed = 0.30;
		}
		else {
			speed = 0.35;
		}
		
		if(1.0 < percentComplete)
			speed = -speed;
		
		//if(Math.abs(speed) <= 0.)
		
		System.out.println("PercentComplete: " + percentComplete);
		System.out.println("YawRemaining: " + yawRemaining());
		System.out.println("Yaw: " + getYaw());
		System.out.println("Final Angle: " + finalAngle);
		System.out.println("Final Angle: " + finalAngle);
//		if(checkOverRotation()){
//			speed = -speed; //Changing speed to reverse if it is over
//		}
		SmartDashboard.putNumber("Speed (NavX)", speed);
		if (angle > 0){
			Robot.driveBase.drive(-speed, speed);
		}
		else{
			Robot.driveBase.drive(speed, -speed);
		}
		
		logExecute(new HashMap<String, Object>() {{
		    put("DegreesToTravel", yawRemaining());
		    put("CurrentAngle", getYaw());
		    put("PercentComplete", percentComplete);
		}});
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Math.abs(1.0 - percentComplete) <= 0.015 && Math.abs(Robot.driveBase.getLeft().getSensorCollection().getQuadratureVelocity()) <= 100) 
			return true;
		else
			return false;
	}

	private boolean isInThreshold(){
//		System.out.println(Math.abs(finalAngle - getYaw()));
		return (Math.abs(yawRemaining()) <= 0.5);
	}
	
	// Called once after isFinished returns true
	protected void end() {
		Robot.driveBase.getLeft().configOpenloopRamp(0.0, 0);
		Robot.driveBase.getRight().configOpenloopRamp(0.0, 0);
		Robot.driveBase.drive(0, 0);
		logFinished(new HashMap<String, Object>() {{
		    put("DegreesToTravel", yawRemaining());
		    put("CurrentAngle", getYaw());
		    put("PercentComplete", percentComplete);
		}});
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.driveBase.drive(0, 0);
	}

	private double getYaw() {
		return Robot.sensors.getYaw() + 180;
	}

	private double yawRemaining() {
		double yaw = getYaw();
		if (angle > 0 && yaw < (startAngle - 10/* Margin of Error Value */)) { // IF
																				// WE
																				// WANT
																				// TO
																				// TURN
																				// 360
																				// THIS
																				// WON't
																				// WORK
			return finalAngle - yaw - 360;
		} else if (angle < 0
				&& yaw > (startAngle + 10/* Margin of Error Value */)) {
			return finalAngle - yaw + 360;
		}
		return finalAngle - yaw;
	}

}