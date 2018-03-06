package paths.left;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.auto_specific.DropCube;
import org.usfirst.frc.team670.robot.commands.drive.Drive;
import org.usfirst.frc.team670.robot.commands.drive.Pivot;
import org.usfirst.frc.team670.robot.commands.elevator.Encoders_Elevator;
import org.usfirst.frc.team670.robot.commands.elevator.Hold_Elevator;
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
    	addSequential((new Drive(Field.DS_TO_SCALE - 28.26/(Math.sin(Math.toRadians(45))) - Robot.length)));
    	addParallel(new Encoders_Elevator(ElevatorState.HIGHSCALE));
    	addSequential(new Pivot(45));
    	addSequential(new Drive(28.26/Math.sin(Math.toRadians(45)) + RoboConstants.FRONT_TO_ELEVATOR + Field.TOLERANCE/2));
    	addSequential(new DropCube());
    	addParallel(new Encoders_Elevator(ElevatorState.SWITCH));
    	addSequential(new Drive(-(RoboConstants.FRONT_TO_ELEVATOR + 28.26/(Math.sin(Math.toRadians(45))))));
    	addSequential(new Pivot(90));
    }
}
