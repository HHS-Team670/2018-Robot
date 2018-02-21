package paths.right;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.auto_specific.Delay;
import org.usfirst.frc.team670.robot.commands.auto_specific.DropCube;
import org.usfirst.frc.team670.robot.commands.auto_specific.SpinIntake;
import org.usfirst.frc.team670.robot.commands.drive.Drive;
import org.usfirst.frc.team670.robot.commands.drive.Pivot;
import org.usfirst.frc.team670.robot.commands.drive.Time_Drive;
import org.usfirst.frc.team670.robot.commands.elevator.Encoders_Elevator;
import org.usfirst.frc.team670.robot.commands.intake.Deploy;
import org.usfirst.frc.team670.robot.constants.Field;
import org.usfirst.frc.team670.robot.constants.RoboConstants;
import org.usfirst.frc.team670.robot.constants.enums.ElevatorState;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *@pre Line up Robot with the switch
 */
public class right_switch_straight extends CommandGroup {

	/**
	 * 
	 * @pre Line up Robot with the switch
	 */
	public right_switch_straight() {
		addParallel(new Encoders_Elevator(ElevatorState.SWITCH));
		addSequential(new Drive(Field.DS_TO_SWITCH - Robot.length - 6));
		addSequential(new DropCube());
    	addSequential(new Drive(-Robot.length/2));
	}
}
