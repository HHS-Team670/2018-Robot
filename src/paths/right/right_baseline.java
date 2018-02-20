package paths.right;

import org.usfirst.frc.team670.robot.commands.drive.Drive;
import org.usfirst.frc.team670.robot.constants.Field;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * Moves forward to baseline, and pauses for 4 seconds - then drives back to
 * starting
 * 
 * 
 */
public class right_baseline extends CommandGroup {

	public right_baseline() {
		addSequential(new Drive(Field.DS_TO_BASELINE + Field.TOLERANCE));
	}
}
