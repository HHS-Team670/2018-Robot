package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.commands.joystick_control.Joystick_Elevator;
import org.usfirst.frc.team670.robot.constants.RoboConstants;
import org.usfirst.frc.team670.robot.constants.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Elevator extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private TalonSRX elevator;
	private SensorCollection encoder;
	private final double PROPORTION_ELEVATOR = 1;
	private final double INTEGRAL_ELEVATOR = 0;
	private final double DERIVATIVE_ELEVATOR = 0;
	
	public Elevator()
	{
		elevator = new TalonSRX(RobotMap.elevatorMotor);
		//elevator.setInverted(true);
		elevator.setSensorPhase(false);
		encoder = new SensorCollection(elevator);
		encoder.setPulseWidthPosition(0, 0);
		elevator.setNeutralMode(NeutralMode.Brake);
		//elevator.configForwardSoftLimitThreshold(RoboConstants.MAX_ELEVATOR_TICKS, RoboConstants.kTimeoutMs);//Higher absolute
		//elevator.configReverseSoftLimitThreshold(RoboConstants.MIN_ELEVATOR_TICKS, RoboConstants.kTimeoutMs);//Lower absolute
		elevator.configForwardSoftLimitEnable(false, 0);
		elevator.configReverseSoftLimitEnable(false, 0);
		//toggleSoftLimits(true);
	}
	
	public double getCurrentPosition()
	{
		return encoder.getQuadraturePosition();
	}
	
	/*public void toggleSoftLimits(boolean limitOn) {
		elevator.configForwardSoftLimitEnable(limitOn, 0);
		elevator.configReverseSoftLimitEnable(limitOn, 0);
	}*/
	
	public void resetEncoder() {
		encoder.setPulseWidthPosition(0, 0);
		encoder.setAnalogPosition(0, 0);
		encoder.setQuadraturePosition(0, 0);
	}
	
	public void moveElevator(double speed)
	{
		elevator.set(ControlMode.PercentOutput, speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Joystick_Elevator());
        //Caleb was here
    }
    
	public double getVelocity()
	{
		return elevator.getSensorCollection().getQuadratureVelocity();
	}
}

