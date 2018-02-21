package paths.right;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.auto_specific.DropCube;
import org.usfirst.frc.team670.robot.commands.auto_specific.PickupCube;
import org.usfirst.frc.team670.robot.commands.drive.Drive;
import org.usfirst.frc.team670.robot.commands.drive.Pivot;
import org.usfirst.frc.team670.robot.commands.drive.Time_Drive;
import org.usfirst.frc.team670.robot.commands.elevator.Encoders_Elevator;
import org.usfirst.frc.team670.robot.constants.Field;
import org.usfirst.frc.team670.robot.constants.enums.ElevatorState;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class right_switch_side extends CommandGroup {

	public right_switch_side() {
		addSequential(new Drive(Field.DS_TO_SWITCH - Robot.length/2 + Field.SWITCH_WIDTH/2));
		addParallel(new Encoders_Elevator(ElevatorState.SWITCH));
		addSequential(new Pivot(-90));
		addSequential(new Drive(Field.SIDE_TO_SWITCH - Robot.width - Field.SIDE_TRIANGLE_WIDTH));
    	addSequential(new DropCube());
    	addParallel(new Encoders_Elevator(ElevatorState.EXCHANGE));
    	addSequential(new Drive(-(Field.SIDE_TO_SWITCH - Robot.width - Field.SIDE_TRIANGLE_WIDTH)));
    	//Addition to go grab cube
    	addSequential(new Pivot(90));
    	addSequential(new Drive(Field.SWITCH_WIDTH));
    	addSequential(new Pivot(135));
    	addParallel(new Time_Drive(2, 0.7));
    	addSequential(new PickupCube());
    	addSequential(new Encoders_Elevator(ElevatorState.SWITCH));
    	addSequential(new DropCube());
	}
}
