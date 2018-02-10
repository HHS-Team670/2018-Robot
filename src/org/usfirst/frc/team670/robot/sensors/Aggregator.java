package org.usfirst.frc.team670.robot.sensors;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;
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
	private AHRS navXMicro;
	private NetworkTable driverstation, rpi;
	
	//Booleans
	private boolean isNavXConnected, encodersConnected, runSensorNetwork, sendDataToDS;
	private int sendCount = 0;
	private double lidar_left, lidar_right;
			
	public Aggregator(){
		runSensorNetwork = true;
		sendDataToDS = true;
		
		//Check the navXMicro is plugged in
	    try {
			navXMicro = new AHRS(RobotMap.navXPort);
			isNavXConnected = true;
		} catch (RuntimeException ex) {
			isNavXConnected = false;
			DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
			navXMicro = null;
		}
	    
		rpi = NetworkTable.getTable("rpi");
	    driverstation = NetworkTable.getTable("driverstation");
	    
	    new Thread(new Runnable() {
	        @Override
	        public void run() {
	        	while(true)
	        	{
	        		sendCount++;
	        		if(runSensorNetwork)
	        		{
			        	lidar_left = rpi.getDouble("lidar_left", -1);
			        	lidar_right = rpi.getDouble("lidar_right", -1);
	        		}
	        		if(sendDataToDS && sendCount > 500)
	        		{
	        			driverstation.putString("operator_state", Robot.oi.getOS().toString());
	        			driverstation.putString("driver_state", Robot.oi.getDS().toString());
			        	driverstation.putDouble("time_left", DriverStation.getInstance().getMatchTime());
			        	driverstation.putString("alliance", DriverStation.getInstance().getAlliance().toString());
			        	driverstation.putDouble("voltage", DriverStation.getInstance().getBatteryVoltage());
			        	driverstation.putBoolean("navX", isNavXConnected);
			        	driverstation.putBoolean("encoders", encodersConnected);
	        		}
	        	}
	        }
	    }).start();
	}
	
	/*@return The distance read in inches by the ultrasonic sensor inside the intake * */
	public double getDistanceIntake()
	{
		return -1;
	}
	
	public void reset() {
		if (isNavXConnected())
			navXMicro.reset();
	}

	public boolean isNavXConnected() {
		try {
			navXMicro = new AHRS(RobotMap.navXPort);
			isNavXConnected = true;
		} catch (RuntimeException ex) {
			isNavXConnected = false;
			DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
			navXMicro = null;
		}
		return isNavXConnected;
	}

	public double getYaw() {
		if (isNavXConnected())
			return navXMicro.getYaw();
		return -1;
	}
	
	public void zeroYaw()
	{
		if(isNavXConnected())
			navXMicro.zeroYaw();
	}

	public double getTilt() {
		if (isNavXConnected())
			return navXMicro.getAngle();
		return -1;	}

	public double getVelocityY() {
		if (isNavXConnected())
			return navXMicro.getVelocityY();
		return -1;	}

	public double getDisplacementX() {
		if (isNavXConnected())
			return navXMicro.getDisplacementX();
		return -1;
	}
	
	public double getDisplacementY() {
		if (isNavXConnected())
			return navXMicro.getDisplacementY();
		return -1;
	}
	
	public double getDisplacementZ() {
		if (isNavXConnected())
			return navXMicro.getDisplacementZ();
		return -1;
	}
	
	public String toString()
	{
		return "";
	}
	
	public String getVal(String data) {

		int lastLessThan = data.lastIndexOf('<');
		int lastGreaterThan = data.lastIndexOf('>');

		if (lastGreaterThan != -1 && lastLessThan != -1) {
			if (lastLessThan < lastGreaterThan) {
				return data.substring(lastLessThan + 1, lastGreaterThan);
			} else if (data.lastIndexOf('<', lastGreaterThan) != -1) {
				return data.substring(data.lastIndexOf('<', lastGreaterThan) + 1, data.lastIndexOf('>'));
			}
		}
		return "-1";
	}

	public void areEncodersWorking(boolean b) {
		encodersConnected = b;
	}
}

