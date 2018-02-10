package paths.right;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.actions.Drive;
import org.usfirst.frc.team670.robot.commands.actions.Pivot;
import org.usfirst.frc.team670.robot.commands.actions.components.Encoders_Elevator;
import org.usfirst.frc.team670.robot.commands.switches.RunIntake;
import org.usfirst.frc.team670.robot.utilities.Constants;
import org.usfirst.frc.team670.robot.utilities.ElevatorState;
import org.usfirst.frc.team670.robot.utilities.Field;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class right_scale_side extends CommandGroup {

	public right_scale_side() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
		
    	addSequential(new Drive(Field.DSToScale - Robot.length + Field.ScaleWidth/2));
    	addSequential(new Pivot(-90));
    	addSequential(new Drive(Field.SideToScale + Field.TOLERANCE - Field.SideTriangleWidth));
		addSequential(new Encoders_Elevator(ElevatorState.SCALE, 0.5)); //Raise Elevator
		addSequential(new Drive(Constants.frontToElevator)); // DRIVE distance from front of robot to elevator arm
		addSequential(new RunIntake(-0.8, Constants.intakeRunTime)); //Place Cube
    	addSequential(new Drive(-1.5 * Robot.length)); //Back Up
		addSequential(new Encoders_Elevator(ElevatorState.DOWN, 0.5)); //Lower Elevator
		addSequential(new Pivot(90));
	}
}
