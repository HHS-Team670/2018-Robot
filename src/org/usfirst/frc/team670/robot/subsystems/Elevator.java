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
	private boolean toggle;

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
		elevator.configOpenloopRamp(0.25, 0);
		// absolute
		elevator.configForwardSoftLimitEnable(false, 0);
		elevator.configReverseSoftLimitEnable(false, 0);
		toggle = true;
		toggleSoftLimits(true);
	}

	public void toggleSoftLimits(boolean b) {
		this.toggle = b;
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
		SmartDashboard.putNumber("SPEED Asking to Elevator", speed);
		if (Robot.intake.isIntakeDeployed()) {
			if (toggle) {
				// Limit is on
				if (getCurrentPosition() > RoboConstants.BOTTOM_ELEVATOR_TICKS) {
					//Speed is negative, so going up
					if (speed > 0)
						elevator.set(ControlMode.PercentOutput, 0);
					else
						elevator.set(ControlMode.PercentOutput, speed);

					SmartDashboard.putString("Is hit Top", "Hit bottom");

				} else if (getCurrentPosition() < RoboConstants.TOP_ELEVATOR_TICKS) {
					//Speed is positive, so going down
					if (speed < 0)
						elevator.set(ControlMode.PercentOutput, 0);
					else
						elevator.set(ControlMode.PercentOutput, speed);
					SmartDashboard.putString("Is hit Top", "Hit top");
				}

				else {
					elevator.set(ControlMode.PercentOutput, speed);
					SmartDashboard.putString("Is hit Top", "In range");
				}
			} else
				elevator.set(ControlMode.PercentOutput, speed);
		} else
			elevator.set(ControlMode.PercentOutput, 0);
		SmartDashboard.putNumber("Ticks", getCurrentPosition());
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Joystick_Elevator());
	}

	/**
	 * 
	 * @param currentTicks
	 * @param maxSpeed
	 * @param goingUp
	 * @return
	 */
	public double calculateSpeed(int currentTicks, double maxSpeed, boolean goingUp) {

		int tolerance = RoboConstants.ELEVATOR_TOLERANCE;
		double minSpeed = RoboConstants.ELEVATOR_MIN_SPEED;
		double speed = 0;

		if (maxSpeed < minSpeed)
			return maxSpeed;

		// MIN_ELEVATOR_TICKS is the top physical point on the elevator, so
		// it's
		// the most negative value
		if (currentTicks > RoboConstants.BOTTOM_ELEVATOR_TICKS - tolerance && !goingUp) {
			speed = ((currentTicks / (RoboConstants.BOTTOM_ELEVATOR_TICKS - tolerance)) * maxSpeed) / 2;
		}

		else if (currentTicks > RoboConstants.ELEVATOR_PULSE_FOR_SECONDSTAGE - tolerance
				&& currentTicks < RoboConstants.ELEVATOR_PULSE_FOR_SECONDSTAGE + tolerance) {

			speed = ((Math.abs(RoboConstants.ELEVATOR_PULSE_FOR_SECONDSTAGE - currentTicks) / tolerance) * maxSpeed);
		}

		// MAX_ELEVATOR_TICKS is the lowest physical point on the elevator, so
		// it's the most positive value
		else if (currentTicks < RoboConstants.TOP_ELEVATOR_TICKS + tolerance && goingUp) {
			speed = ((RoboConstants.TOP_ELEVATOR_TICKS + currentTicks / tolerance) * maxSpeed) / 2;

		} else
			speed = maxSpeed;

		if (speed < minSpeed)
			speed = minSpeed;

		return speed;

	}
}
