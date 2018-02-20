package paths.left;

import org.usfirst.frc.team670.robot.commands.drive.Drive;
import org.usfirst.frc.team670.robot.constants.Field;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class left_baseline extends CommandGroup {

    public left_baseline() {
    	addSequential(new Drive(Field.DS_TO_BASELINE + Field.TOLERANCE));    	
    }
}
