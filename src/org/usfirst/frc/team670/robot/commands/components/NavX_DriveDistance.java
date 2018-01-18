package org.usfirst.frc.team670.robot.commands.components;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * Uses a PID control loop plus the navX getDisplacement to move a given distance in feet
 * 
 * @author shayl
 *
 */
public class NavX_DriveDistance extends Command{

	private double distance, finalDistance, initialDisplacement;
    private double integral, derivative, previous_error = 0;
    private double startYaw;
    private double P = 0.5, I = 0, D = 0;
    private double lSpeed, rSpeed;

	public NavX_DriveDistance(double feet) {
		// Use requires() here to declare subsystem dependencies
		distance = feet;
		lSpeed = 0;
		rSpeed = 0;
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		initialDisplacement = getDisplacement();
		finalDistance = initialDisplacement + distance;
		startYaw = getYaw();
		integral = 0;
		previous_error = 0;
		derivative = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double percentComplete = (distance - Math.abs((getDisplacement() - initialDisplacement)))/distance;
		double error = finalDistance - getDisplacement(); // Error = Target - Actual
        this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
        derivative = (error - this.previous_error) / .02;
        lSpeed = P*error + I*this.integral + D*derivative;
        rSpeed = P*error + I*this.integral + D*derivative;
        previous_error = error;
        System.out.println("Displacement: " + getDisplacement());
        System.out.println("Y: " + Robot.sensors.getDisplacementY());
        System.out.println("X: " + Robot.sensors.getDisplacementX());
//    	if(startYaw - getYaw() > 0.5) { 
//    		lSpeed += 0.01;
//    		rSpeed -= 0.01;
//    	}
//    	else if(startYaw - getYaw() < 0.5) {
//    		rSpeed += 0.01;
//    		lSpeed -= 0.01;
//    	}        
//        if(percentComplete < 0.5){
//        	lSpeed = 2*percentComplete;
//        	rSpeed = 2*percentComplete;
//        }
//        else{
//        	lSpeed = 1- percentComplete;
//        	rSpeed = 1- percentComplete;
//        }
        	
		
		if(distance > 0)
			Robot.driveBase.drive(lSpeed, rSpeed);
		else if(distance < 0) 
			Robot.driveBase.drive(-lSpeed, -rSpeed);
		
		SmartDashboard.putString("final distance", finalDistance + "");
		SmartDashboard.putString("displacement", getDisplacement() + "");

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		if(finalDistance - getDisplacement() < 0.1)
		{
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
		end();
	}
	
	/**
	 * Gets Displacement in feet
	 */
	private double getDisplacement() {
		return Robot.sensors.getDisplacementX() * 3.28084;
	}
	
	private double getYaw() {
		return Robot.sensors.getYaw() + 180;
	}
	
}
