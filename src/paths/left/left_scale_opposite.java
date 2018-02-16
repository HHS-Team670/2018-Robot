package paths.left;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.drive.Drive;
import org.usfirst.frc.team670.robot.commands.drive.Pivot;
import org.usfirst.frc.team670.robot.commands.drive.Time_Drive;
import org.usfirst.frc.team670.robot.commands.elevator.Encoders_Elevator;
import org.usfirst.frc.team670.robot.commands.intake.Deploy;
import org.usfirst.frc.team670.robot.commands.intake.DropCube;
import org.usfirst.frc.team670.robot.constants.Field;
import org.usfirst.frc.team670.robot.constants.RoboConstants;
import org.usfirst.frc.team670.robot.constants.enums.ElevatorState;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class left_scale_opposite extends CommandGroup {

	public left_scale_opposite() {
		// Add Commands here:
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
		addSequential(new Drive(Field.DS_TO_SWITCH + Field.SWITCH_WIDTH - Robot.length + 0.5*(Field.DS_TO_PLATFORM - Field.DS_TO_SWITCH - Field.SWITCH_WIDTH)));
		addSequential(new Pivot(90));
		addSequential(new Drive(Field.DRIVER_SIDE_LENGTH - 2 * Field.SIDE_TRIANGLE_WIDTH - Robot.width));
		addSequential(new Pivot(-90));
		addSequential(new Drive(Field.DS_TO_SCALE - (Field.DS_TO_SWITCH + Field.SWITCH_WIDTH - Robot.length) + Robot.width));
		addSequential(new Pivot(-90));
		addSequential(new Encoders_Elevator(ElevatorState.HIGHSCALE)); //Raise Elevator
		addSequential(new Drive(Field.SIDE_TO_SCALE - Field.SIDE_TRIANGLE_WIDTH - Robot.length + RoboConstants.FRONT_TO_ELEVATOR)); // DRIVE distance from front of robot to elevator arm
    	addSequential(new DropCube());
    	
    	addSequential(new Encoders_Elevator(ElevatorState.EXCHANGE));
    	addSequential(new Drive(-(Field.SIDE_TO_SCALE - Field.SIDE_TRIANGLE_WIDTH - Robot.length + RoboConstants.FRONT_TO_ELEVATOR)));
    	addSequential(new Pivot(-90));
    	addSequential(new Drive(Field.DS_TO_SCALE - (Field.DS_TO_SWITCH + Field.SWITCH_WIDTH - Robot.length) + Robot.width));
    	addSequential(new Pivot(90));
    	addSequential(new Time_Drive(1.5, -0.75));
	}
}
