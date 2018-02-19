package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.joystick_control.Joystick_Elevator;
import org.usfirst.frc.team670.robot.constants.RoboConstants;
import org.usfirst.frc.team670.robot.constants.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private TalonSRX elevator;
	private SensorCollection encoder;
	private boolean toggle = true;

	public Elevator() {
		elevator = new TalonSRX(RobotMap.elevatorMotor);
		// elevator.setInverted(true);
		// elevator.setSensorPhase(false);
		encoder = new SensorCollection(elevator);
		encoder.setPulseWidthPosition(0, 0);
		elevator.setNeutralMode(NeutralMode.Brake);
		// elevator.configForwardSoftLimitThreshold(RoboConstants.MAX_ELEVATOR_TICKS,
		// RoboConstants.kTimeoutMs);// Higher
		// absolute
		// elevator.configReverseSoftLimitThreshold(RoboConstants.MIN_ELEVATOR_TICKS,
		// RoboConstants.kTimeoutMs);// Lower
		elevator.configClosedloopRamp(3, 0);
		// absolute
		elevator.configForwardSoftLimitEnable(false, 0);
		elevator.configReverseSoftLimitEnable(false, 0);
		toggle = false;
		// toggleSoftLimits(true);
	}

	public double getCurrentPosition() {
		return encoder.getQuadraturePosition();
	}

	public double getCurrentVelocity() {
		return encoder.getQuadratureVelocity();
	}

	/*
	 * public void toggleSoftLimits(boolean limitOn) {
	 * elevator.configForwardSoftLimitEnable(limitOn, 0);
	 * elevator.configReverseSoftLimitEnable(limitOn, 0); }
	 */

	public void resetEncoder() {
		encoder.setPulseWidthPosition(0, 0);
		encoder.setAnalogPosition(0, 0);
		encoder.setQuadraturePosition(0, 0);
	}

	public void moveElevator(double speed) {
		if (Robot.intake.isIntakeDeployed()) {
			if (toggle) {
				// Limit is on
				if (getCurrentPosition() > RoboConstants.MAX_ELEVATOR_TICKS)
					elevator.set(ControlMode.PercentOutput, -.2);
				else if (getCurrentPosition() < RoboConstants.MIN_ELEVATOR_TICKS)
					elevator.set(ControlMode.PercentOutput, .2);
				else
					elevator.set(ControlMode.PercentOutput, speed);
			} else
				elevator.set(ControlMode.PercentOutput, speed * 0.3);
		} else
			elevator.set(ControlMode.PercentOutput, 0);
		SmartDashboard.putNumber("Ticks", getCurrentPosition());
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Joystick_Elevator());
	}

	public double calculateSpeed(int currentTicks, int tolerance, double maxSpeed, double minSpeed) {

		if (maxSpeed < minSpeed)
			return maxSpeed;

		// MIN_ELEVATOR_TICKS is the lowest physical point on the elevator, so
		// it's
		// the most positive value
		if (currentTicks > RoboConstants.MIN_ELEVATOR_TICKS - tolerance) {
			return ((currentTicks / (RoboConstants.MIN_ELEVATOR_TICKS - tolerance)) * maxSpeed);
		}

		else if (currentTicks > RoboConstants.ELEVATOR_PULSE_FOR_SECONDSTAGE - tolerance
				&& currentTicks < RoboConstants.ELEVATOR_PULSE_FOR_SECONDSTAGE + tolerance) {

			return ((Math.abs(RoboConstants.ELEVATOR_PULSE_FOR_SECONDSTAGE - currentTicks) / tolerance) * maxSpeed)
					+ minSpeed;
		}

		// MAX_ELEVATOR_TICKS is the highest physical point on the elevator, so
		// it's the most negative value
		else if (currentTicks < RoboConstants.MAX_ELEVATOR_TICKS + tolerance) {
			return ((RoboConstants.MAX_ELEVATOR_TICKS + currentTicks / tolerance) * maxSpeed);

		}

		return maxSpeed;

	}

	public void toggleConstants(boolean toggle) {
		this.toggle = toggle;
	}
}
