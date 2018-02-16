package org.usfirst.frc.team670.robot.constants;

public class RoboConstants {

	// Gear ratio from motor to shaft = 10.71
	// Ratio encoder to shaft = 1.0

	// All PID Variables
	public static final int kSlotIdx = 0;
	public static final int kPIDLoopIdx = 0;
	public static final int kTimeoutMs = 10;
	public static final double Proportion = .09, // Make P higher --> Set to 0.05 yesterday
			Integral = 0, Derivative = 0;

	// Ticks for one rotation of a drivebase wheel
	public static final double drivebaseTickPerRotation = 4096.0;

	// Pivot radius of the robot
	public static final double pivotRadius = 17.51;

	// Wheel velocity = inches/second at power of timeAutoSpeed;
	public static final double wheelVelocity = 0; // THESE NEED TO BE SET
	public static final double timeAutoSpeed = 0;

	// Seconds to run intake during auto
	public static final double intakeRunTime = 5;

	// Distance from the front of the robot to the elevator
	public static final double frontToElevator = 12.5;

	// Diameter of wheels in drivebase
	public static final double DIAMETERinInchesDriveBase = 6;
	public static final double gearRatioDB = 1.0;
	// Diameter of wheel in elevator
	public static final double DIAMETERinInchesElevator = 6;

	// Elevator Heights
	public static final double elevatorPulseForExchange = 0; // SET THESE
	public static final double elevatorPulseForSwitch = 0;
	public static final double elevatorPulseForHighScale = 0;
	public static final double elevatorPulseForMidScale = 0;
	public static final double elevatorAutonSpeed = 0.05; // Probably make this higher after testing

	public static final int maxElevatorTicks = 0; // SET THIS
	public static final int minElevatorTicks = 0; // SET THIS

	public static final double maxIntakeVoltage = 0;

	// Multiply by distance in inches to get needed ticks
	public static final double elevatorInchesToTicks = 8 / 3 * drivebaseTickPerRotation / Math.PI
			* DIAMETERinInchesDriveBase;
	public static final double elevatorTicksToInches = Math.PI * DIAMETERinInchesDriveBase
			/ (8 / 3 * drivebaseTickPerRotation);

	public static final double ProportionElevator = 1;
	public static final double IntegralElevator = 0;
	public static final double DerivativeElevator = 0;

	public static double elevatorTicksToGo(double inches) {
		return inches * elevatorInchesToTicks;
	}

	public static double elevatorInchesToGo(double ticks) {
		return ticks * elevatorTicksToInches;
	}

}