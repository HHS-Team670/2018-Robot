package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.constants.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * Program to consolidate all sensor information - one class to retrieve information from
 * 
 * @author vsharma
 *
 */
public class Aggregator extends Thread{
	
	// Sensors
	//private AHRS navXMicro;
	private NetworkTable driverstation, knuckles;
	private double angle = 0, x, y, w, h;
	private AnalogInput aio;
	
	//Booleans
	private boolean isNavXConnected, encodersConnected, elevatorEncoders;
	private boolean sendDataToDS;
	
	public Aggregator(){
		sendDataToDS = true;
		
	    driverstation = NetworkTable.getTable("driverstation");
		knuckles = NetworkTable.getTable("knuckles_vision");
		
		//Check the navXMicro is plugged in
	  /*  try {
			navXMicro = new AHRS(RobotMap.navXPort);
			isNavXConnected = true;
		} catch (RuntimeException ex) {
			isNavXConnected = false;
			DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
			navXMicro = null;
		}*/
	    	    
	    aio = new AnalogInput(0);
	    	    
	    new Thread(new Runnable() {
	        @Override
	        public void run() {
	        	while(true)
	        	{
	        		if(sendDataToDS)
	        		{
	        			driverstation.putString("operator_state", Robot.oi.getOS().toString());
	        			driverstation.putString("driver_state", Robot.oi.getDS().toString());
			        	driverstation.putDouble("time_left", DriverStation.getInstance().getMatchTime());
			        	driverstation.putString("alliance", DriverStation.getInstance().getAlliance().toString());
			        	driverstation.putDouble("voltage", DriverStation.getInstance().getBatteryVoltage());
			        	driverstation.putBoolean("navX", isNavXConnected);
			        	driverstation.putBoolean("encoders", encodersConnected);
			        	driverstation.putBoolean("elevator_encoders", elevatorEncoders);
			        	driverstation.putBoolean("isIntakeOpen", Robot.intake.isIntakeOpen());
			        	driverstation.putBoolean("isIntakeDeployed", Robot.intake.isIntakeDeployed());
			        	angle = knuckles.getNumber("angle", 0);
			        	x = knuckles.getNumber("x", 0);
			        	y = knuckles.getNumber("y", 0);
			        	w = knuckles.getNumber("w", 0);
			        	h = knuckles.getNumber("h", 0);
	        		}
	        	}
	        }
	    }).start();
	}
	
	public double getDistanceIntakeInches()
	{
		return aio.getValue()/19.6;
	}
	
	public double getAngle()
	{
		return angle;
	}
	
	public void reset() {
		Robot.resetNavX();
	}

	public boolean isNavXConnected() {
	/*	try {
			navXMicro = new AHRS(RobotMap.navXPort);
			isNavXConnected = true;
		} catch (RuntimeException ex) {
			isNavXConnected = false;
			DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
			navXMicro = null;
		}
		return isNavXConnected;*/
		return Robot.isNavXConnected();
	}

	public static double getYaw() {
		/*if (isNavXConnected())
			return navXMicro.getYaw();*/
		return Robot.getYaw();
	}
	
	public void areEncodersWorking(boolean b) {
		encodersConnected = b;
	}

	public void areElevatorEncodersWorking(boolean b) {
		elevatorEncoders = b;
	}

	public double getWidth() {
		return w;
	}

	public boolean isVisionConnected() {
		return knuckles.isConnected();
	}
	
}

