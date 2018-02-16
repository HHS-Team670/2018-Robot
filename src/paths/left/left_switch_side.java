package paths.left;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.actions.Deploy;
import org.usfirst.frc.team670.robot.commands.actions.Drive;
import org.usfirst.frc.team670.robot.commands.actions.DropCube;
import org.usfirst.frc.team670.robot.commands.actions.Pivot;
import org.usfirst.frc.team670.robot.commands.actions.components.Encoders_Elevator;
import org.usfirst.frc.team670.robot.constants.ElevatorState;
import org.usfirst.frc.team670.robot.constants.Field;
import org.usfirst.frc.team670.robot.constants.RoboConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class left_switch_side extends CommandGroup {

	public left_switch_side() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		//      addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		//      addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
		addParallel(new Deploy(true));
		addSequential(new Drive(Field.DS_TO_SWITCH - Robot.length/2 + Field.SWITCH_WIDTH/2));
		addSequential(new Pivot(90));
		addSequential(new Encoders_Elevator(ElevatorState.SWITCH));
		addSequential(new Drive(Field.SIDE_TO_SWITCH - Robot.width - Field.SIDE_TRIANGLE_WIDTH));
    	addSequential(new DropCube());
    	addSequential(new Drive(-(Field.SIDE_TO_SWITCH - Robot.width - Field.SIDE_TRIANGLE_WIDTH)));
    	addParallel(new Encoders_Elevator(ElevatorState.EXCHANGE));
    	addSequential(new Pivot(-90));
    	addSequential(new Drive((Field.DS_TO_PLATFORM - (Field.DS_TO_SWITCH + Field.SWITCH_WIDTH))/2 + Robot.length/2 ));
    	addSequential(new Pivot(90));
	}
}
