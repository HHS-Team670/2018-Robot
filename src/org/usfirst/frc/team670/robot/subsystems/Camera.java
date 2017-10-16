package org.usfirst.frc.team670.robot.subsystems;

import org.opencv.core.Mat;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Camera extends Subsystem {
	
	private UsbCamera usbCam;
	private CvSource outputStream;
	private CvSink CvSink;
	
	public Camera()
	{
		
	}
	
	//https://github.com/SCOTS-Bots/FRC-2016-Robot-Java/blob/master/src/org/scotsbots/robotbase/utils/CustomCameraServer.java
	public void init()
	{
		usbCam = CameraServer.getInstance().startAutomaticCapture("front", 2);
        CvSink = CameraServer.getInstance().getVideo("winch");
        outputStream = CameraServer.getInstance().putVideo("Video", 640, 480);
        
        //Run thread to start the processing
        new Thread() 
		{
			public void run()
			{
			    Mat src = new Mat();
				while(true)
	        	{
				        CvSink.grabFrame(src);
				        
				        outputStream.putFrame(src);
				        
	        	}
			}
		}.start();
	}
	
    public void initDefaultCommand() {
        //setDefaultCommand(new MySpecialCommand());
    }
}