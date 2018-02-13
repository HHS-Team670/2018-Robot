package org.usfirst.frc.team670.robot.commands.joysticks;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.constants.DriverState;
import org.usfirst.frc.team670.robot.constants.RoboConstants;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class Joystick_Drive extends Command {

	private double rSpeed;
	private double lSpeed;
	private Joystick joy;

	public Joystick_Drive() {
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		joy = Robot.oi.getLeftStick();
		Robot.driveBase.initJoystickDrive();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double x, ly, ry;
		
		/*double power = 10.0*(1 - (Robot.elevator.getCurrentPosition())/(Constants.maxElevatorTicks * 1.0));
		if(power < 0.5)
			power = 0.5;
		//LeftStick X
		if(Robot.oi.getLeftStick().getX() < 0.0)
			x = -Math.pow(Robot.oi.getLeftStick().getX(), power);
		else
			x = Math.pow(Robot.oi.getLeftStick().getX(), power);
		//LeftStick Y
		if(Robot.oi.getLeftStick().getY() < 0.0)
			ly = Math.pow(Robot.oi.getLeftStick().getY() ,power);
		else
			ly = Math.pow(Robot.oi.getLeftStick().getY() ,power);
		//RightStick Y
		if(Robot.oi.getRightStick().getY() < 0.0)
			ry = Math.pow(Robot.oi.getRightStick().getY(), power);
		else
			ry = Math.pow(Robot.oi.getRightStick().getY(), power);
		*/
		ry = Robot.oi.getRightStick().getY();
		ly = Robot.oi.getLeftStick().getY();
		x = Robot.oi.getLeftStick().getX();

		ry = Math.pow(ry, 3);
		ly = Math.pow(ly, 3);
		x = Math.pow(x, 3);
		
		if (Robot.oi.getDS().equals(DriverState.TANKREVERSE)) 
			Robot.driveBase.drive(-ly, -ry);
		else if (Robot.oi.getDS().equals(DriverState.SINGLE))
			PartyDrive(x, ly);
		else
			Robot.driveBase.drive(ly, ry);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
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

	private void PartyDrive(double x, double y)
	{
		double forwrd = y * -1; /* Invert stick Y axis */
		double strafe = x;
		 
		/* Adjust Joystick X/Y inputs by navX MXP yaw angle */
		
		double gyro_degrees = Robot.sensors.getYaw();
		double gyro_radians = gyro_degrees * Math.PI/180; 
		double temp = forwrd * Math.cos(gyro_radians) + 
		strafe * Math.sin(gyro_radians);
		strafe = -forwrd * Math.sin(gyro_radians) + strafe * Math.cos(gyro_radians);
		forwrd = temp;

		/* At this point, Joystick X/Y (strafe/forwrd) vectors have been */
		/* rotated by the gyro angle, and can be sent to drive system */
		singleStickDrive(strafe, forwrd);
	}
	
	public void singleStickDrive(double x, double y) {
		rSpeed = -x - y;
		lSpeed = -x + y;
		lSpeed = 0.5 * Math.pow(lSpeed, 3) + (1 - 0.5) * lSpeed;
		rSpeed = 0.5 * Math.pow(rSpeed, 3) + (1 - 0.5) * rSpeed;
		Robot.driveBase.drive(lSpeed, rSpeed);
	}
}