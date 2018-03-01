package paths.center;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.drive.Drive;
import org.usfirst.frc.team670.robot.commands.elevator.Encoders_Elevator;
import org.usfirst.frc.team670.robot.constants.Field;
import org.usfirst.frc.team670.robot.constants.enums.ElevatorState;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *@pre starts next to the exchange
 */
public class center_baseline extends CommandGroup {

	/*
	 * Drive robot length may not be ending, maybe make it a slightly longer distance to insure it finishes
	 */
    public center_baseline() {
    	addParallel(new Encoders_Elevator(ElevatorState.SWITCH));
		addSequential(new Drive(Field.DS_TO_SWITCH - Robot.length - 6));
    }
}

