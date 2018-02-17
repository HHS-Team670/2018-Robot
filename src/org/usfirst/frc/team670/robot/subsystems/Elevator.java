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
		encoder = new SensorCollection(elevator);
		encoder.setPulseWidthPosition(0, 0);
		//elevator.configForwardSoftLimitThreshold(RoboConstants.maxElevatorTicks, RoboConstants.kTimeoutMs);
		//elevator.configReverseSoftLimitThreshold(RoboConstants.minElevatorTicks, RoboConstants.kTimeoutMs);
		//elevator.configForwardSoftLimitEnable(true, RoboConstants.kTimeoutMs);
		//elevator.configReverseSoftLimitEnable(true, RoboConstants.kTimeoutMs);
	}
	
	/*
	*	- command to move elevator all the way down slowly and reset encoders
	*	- soft limits
	*	- Heights:
	*	    - Exchange = low as possible
	*	    - Switch = 35
	*	    - Mid Scale = 71
	*	    - High Scale = 83
	*	    - Also do second stage
	*	    - Full height
	 */
	
	public void initPID(TalonSRX talon) {
		int absolutePosition = talon.getSelectedSensorPosition(RoboConstants.kTimeoutMs)
				& 0xFFF; 
		talon.setSelectedSensorPosition(absolutePosition, RoboConstants.kPIDLoopIdx, RoboConstants.kTimeoutMs);

		talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, RoboConstants.kPIDLoopIdx, RoboConstants.kTimeoutMs);
		talon.setSensorPhase(true);

		talon.configNominalOutputForward(0, RoboConstants.kTimeoutMs);
		talon.configNominalOutputReverse(0, RoboConstants.kTimeoutMs);
		talon.configPeakOutputForward(1, RoboConstants.kTimeoutMs);
		talon.configPeakOutputReverse(-1, RoboConstants.kTimeoutMs);
		talon.configAllowableClosedloopError(0, RoboConstants.kPIDLoopIdx,
				RoboConstants.kTimeoutMs); 	
		talon.config_kF(RoboConstants.kPIDLoopIdx, 0.0, RoboConstants.kTimeoutMs);
		talon.config_kP(RoboConstants.kPIDLoopIdx, PROPORTION_ELEVATOR, RoboConstants.kTimeoutMs);
		talon.config_kI(RoboConstants.kPIDLoopIdx, INTEGRAL_ELEVATOR, RoboConstants.kTimeoutMs);
		talon.config_kD(RoboConstants.kPIDLoopIdx, DERIVATIVE_ELEVATOR, RoboConstants.kTimeoutMs);
	}
	
	public double getCurrentPosition()
	{
		//return encoder.getPulseWidthPosition();
		return 0;
	}
	
	public void toggleSoftLimits(boolean limitOn) {
		elevator.configForwardSoftLimitEnable(limitOn, 0);
		elevator.configReverseSoftLimitEnable(limitOn, 0);
	}
	
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
    }
    
	public TalonSRX getTalon() {
		return elevator;
	}
}

