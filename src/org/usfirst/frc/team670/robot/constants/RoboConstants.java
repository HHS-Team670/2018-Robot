package org.usfirst.frc.team670.robot.constants;

public class RoboConstants {

	// Gear ratio from motor to shaft = 10.71
	// Ratio encoder to shaft = 1.0

	// All PID Variables
	public static final int kSlotIdx = 0;
	public static final int kPIDLoopIdx = 0;
	public static final int kTimeoutMs = 10;
	public static final double PROPORTION = .09, // Make P higher --> Set to 0.05 yesterday
			INTEGRAL = 0, DERIVATIVE = 0;

	// Ticks for one rotation of a drivebase wheel
	public static final double DRIVEBASE_TICKS_PER_ROTATION = 4096.0;

	// Pivot radius of the robot in degrees
	public static final double PIVOT_RADIUS = 17.51;

	// Wheel velocity = inches/second at power of timeAutoSpeed;
	public static final double WHEEL_VELOCITY = 0; // THESE NEED TO BE SET
	public static final double TIME_AUTO_SPEED = 0;

	// Seconds to run intake during auto
	public static final double INTAKE_RUN_TIME = 5;

	// Distance from the front of the robot to the elevator
	public static final double FRONT_TO_ELEVATOR = 12.5;

	// Diameter of wheels in drivebase in inches
	public static final double DRIVEBASE_WHEEL_DIAMETER = 6;
	public static final double DRIVEBASE_GEAR_RATIO = 1.0;
	// Diameter of wheel in elevator in inches
	public static final double ELEVATOR_WHEEL_DIAMETER = 6; //Not sure if this is correct

	// Elevator Heights
	public static final double ELEVATOR_PULSE_FOR_EXCHANGE = 0; // SET THESE
	public static final double ELEVATOR_PULSE_FOR_SWITCH = 0;
	public static final double ELEVATOR_PULSE_FOR_HIGHSCALE = 0;
	public static final double ELEVATOR_PULSE_FOR_MIDSCALE = 0;
	public static final double ELEVATOR_AUTON_SPEED = 0.05; // Probably make this higher after testing

	public static final int MAX_ELEVATOR_TICKS = 0; // SET THIS
	public static final int MIN_ELEVATOR_TICKS = 0; // SET THIS

	public static final double MAX_INTAKE_VOLTAGE = 10; //You're gonna want to set this, amde it nonzero to avoid repeatedly printing warning

	// Multiply by distance in inches to get needed ticks
	public static final double elevatorInchesToTicks = 8 / 3 * DRIVEBASE_TICKS_PER_ROTATION / Math.PI
			* DRIVEBASE_WHEEL_DIAMETER;
	public static final double elevatorTicksToInches = Math.PI * DRIVEBASE_WHEEL_DIAMETER
			/ (8 / 3 * DRIVEBASE_TICKS_PER_ROTATION);

	public static double elevatorTicksToGo(double inches) {
		return inches * elevatorInchesToTicks;
	}

	public static double elevatorInchesToGo(double ticks) {
		return ticks * elevatorTicksToInches;
	}

}