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
		addParallel(new Deploy(true));
    	addSequential(new Drive(Field.DS_TO_SCALE - Robot.length/2 + Field.SCALE_WIDTH/2 - 42));
    	addSequential(new Pivot(+45));
    	addParallel(new Drive(-24));
    	addSequential(new Encoders_Elevator(ElevatorState.HIGHSCALE));//Raise Elevator
    	addParallel(new Hold_Elevator(true));
    	addSequential(new Drive(18));
    	addSequential(new DropCube());
    	addSequential(new Drive(-24));
    	addSequential(new Encoders_Elevator(ElevatorState.EXCHANGE));
    }
}
