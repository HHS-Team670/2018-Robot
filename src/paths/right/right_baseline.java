package paths.right;

import org.usfirst.frc.team670.robot.commands.autonomous.actions.Encoders_DriveDistance;
import org.usfirst.frc.team670.robot.commands.autonomous.actions.Time_Delay;
import org.usfirst.frc.team670.robot.utilities.Field;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class right_baseline extends CommandGroup {

	public right_baseline() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
		
    	addSequential(new Encoders_DriveDistance(Field.DSToBaseline + Field.TOLERANCE));
    	addSequential(new Time_Delay(4));
    	addSequential(new Encoders_DriveDistance(-Field.DSToBaseline));
	}
}
