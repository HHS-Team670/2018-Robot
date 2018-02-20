package paths.left;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.auto_specific.DropCube;
import org.usfirst.frc.team670.robot.commands.drive.Drive;
import org.usfirst.frc.team670.robot.commands.drive.Pivot;
import org.usfirst.frc.team670.robot.commands.elevator.Encoders_Elevator;
import org.usfirst.frc.team670.robot.commands.intake.Deploy;
import org.usfirst.frc.team670.robot.constants.Field;
import org.usfirst.frc.team670.robot.constants.RoboConstants;
import org.usfirst.frc.team670.robot.constants.enums.ElevatorState;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class left_scale_side extends CommandGroup {

    public left_scale_side() {
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
    	addSequential(new Drive(Field.DS_TO_SCALE - Robot.length/2 + Field.SCALE_WIDTH/2));
    	addSequential(new Pivot(90));
    	addParallel(new Drive(-Field.SCALE_SIDE_BACKUP));
    	addSequential(new Encoders_Elevator(ElevatorState.HIGHSCALE));//Raise Elevator
    	addSequential(new Drive(Field.SCALE_SIDE_BACKUP + Field.SIDE_TO_SCALE - Field.SIDE_TRIANGLE_WIDTH - Robot.width + RoboConstants.FRONT_TO_ELEVATOR));
    	addSequential(new DropCube());
    	addParallel(new Encoders_Elevator(ElevatorState.EXCHANGE));
    	addSequential(new Drive(-(Field.SIDE_TO_SCALE - Field.SIDE_TRIANGLE_WIDTH - Robot.width + RoboConstants.FRONT_TO_ELEVATOR)));
    	addSequential(new Pivot(90));
    	addSequential(new Drive(Field.DS_TO_SCALE - (Field.DS_TO_SWITCH + Field.SWITCH_WIDTH - Robot.length) + Robot.width));
    	addSequential(new Pivot(-90));
    }
}
