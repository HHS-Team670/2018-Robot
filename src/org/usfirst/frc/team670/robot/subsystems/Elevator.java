package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.joysticks.Joystick_Elevator;
import org.usfirst.frc.team670.robot.constants.RoboConstants;

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
	
	public Elevator()
	{
		elevator = new TalonSRX(RobotMap.elevatorMotor);
		encoder = new SensorCollection(elevator);
		encoder.setPulseWidthPosition(0, 0);
		elevator.configForwardSoftLimitThreshold(RoboConstants.maxElevatorTicks, RoboConstants.kTimeoutMs);
		elevator.configReverseSoftLimitThreshold(RoboConstants.minElevatorTicks, RoboConstants.kTimeoutMs);
		elevator.configForwardSoftLimitEnable(true, RoboConstants.kTimeoutMs);
		elevator.configReverseSoftLimitEnable(true, RoboConstants.kTimeoutMs);
	}
	
	public void initPID(TalonSRX talon) {
		int absolutePosition = talon.getSelectedSensorPosition(RoboConstants.kTimeoutMs)
				& 0xFFF; /*
							 * mask out the bottom12 bits, we don't care about
							 * the wrap arounds
							 */
		/* use the low level API to set the quad encoder signal */
		talon.setSelectedSensorPosition(absolutePosition, RoboConstants.kPIDLoopIdx, RoboConstants.kTimeoutMs);

		/* choose the sensor and sensor direction */
		talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, RoboConstants.kPIDLoopIdx, RoboConstants.kTimeoutMs);
		talon.setSensorPhase(true);

		/* set the peak and nominal outputs, 12V means full */
		talon.configNominalOutputForward(0, RoboConstants.kTimeoutMs);
		talon.configNominalOutputReverse(0, RoboConstants.kTimeoutMs);
		talon.configPeakOutputForward(1, RoboConstants.kTimeoutMs);
		talon.configPeakOutputReverse(-1, RoboConstants.kTimeoutMs);
		/*
		 * set the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		talon.configAllowableClosedloopError(0, RoboConstants.kPIDLoopIdx,
				RoboConstants.kTimeoutMs); /* always servo */
		/* set closed loop gains in slot0 */
		talon.config_kF(RoboConstants.kPIDLoopIdx, 0.0, RoboConstants.kTimeoutMs);
		talon.config_kP(RoboConstants.kPIDLoopIdx, RoboConstants.ProportionElevator, RoboConstants.kTimeoutMs);
		talon.config_kI(RoboConstants.kPIDLoopIdx, RoboConstants.IntegralElevator, RoboConstants.kTimeoutMs);
		talon.config_kD(RoboConstants.kPIDLoopIdx, RoboConstants.DerivativeElevator, RoboConstants.kTimeoutMs);
	}
	
	public double getCurrentPosition()
	{
		return encoder.getPulseWidthPosition();
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

